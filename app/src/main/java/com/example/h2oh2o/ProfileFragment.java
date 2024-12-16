package com.example.h2oh2o;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Fragment BonusesFragment;
    private View viewBonusesButton;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private EditText meterInput, peopleInput;
    private TextView resultOutput;
    private Button calculateButton;

    // Норма потребления воды на одного человека в месяц (м³)
    private static final float NORMAL_CONSUMPTION_PER_PERSON = 3.5f; // Норма на одного человека в м³

    public void WaterConsumptionFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Найдите ImageView по id
        ImageView imageView = view.findViewById(R.id.imageView4);  // imageView4 — это id вашей картинки

        meterInput = view.findViewById(R.id.meterInput);
        peopleInput = view.findViewById(R.id.peopleInput);
        resultOutput = view.findViewById(R.id.resultOutput);
        calculateButton = view.findViewById(R.id.calculateButton);


        // Установите обработчик нажатия на картинку
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создайте Intent для перехода на новую активность
                Intent intent = new Intent(getActivity(), BDProfileActivity.class); // BDProfileActivity — это ваша новая активность
                startActivity(intent);
            }
        });



        // Установите обработчик нажатия на кнопку для расчета потребления воды
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов метода расчета
                calculateWaterConsumption();
            }
        });

        return view;

    }

    private void calculateWaterConsumption() {
        String meterText = meterInput.getText().toString();
        String peopleText = peopleInput.getText().toString();

        if (meterText.isEmpty() || peopleText.isEmpty()) {
            resultOutput.setText("Пожалуйста, заполните все поля.");
            return;
        }

        // Преобразование ввода в числа
        float meterReading = Float.parseFloat(meterText);
        int peopleCount = Integer.parseInt(peopleText);

        float totalNormalConsumption = NORMAL_CONSUMPTION_PER_PERSON * peopleCount;

        if (meterReading < totalNormalConsumption) {
            float waterSaved = totalNormalConsumption - meterReading;
            resultOutput.setText("Вы сэкономили: " + waterSaved + " м³ воды.");

            // Добавление бонусов
            int bonusPoints = (int) (waterSaved * 1000); // 1000 бонусов за 1 м³ воды
            BonusViewModel.addBonusPoints(bonusPoints);
        } else if (meterReading > totalNormalConsumption) {
            float excessWater = meterReading - totalNormalConsumption;
            resultOutput.setText("Вы превысили норму на: " + excessWater + " м³ воды.");
        } else {
            resultOutput.setText("Вы соблюдаете норму потребления воды.");
        }
    }




}