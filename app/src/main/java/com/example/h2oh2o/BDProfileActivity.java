package com.example.h2oh2o;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BDProfileActivity extends AppCompatActivity {
    private TextView nameTextView, emailTextView, phoneTextView, addressTextView;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdprofile);
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        addressTextView = findViewById(R.id.addressTextView);

        // Создание экземпляра DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Извлечение данных пользователя из базы данных
        displayUserData();

        Button backButton = findViewById(R.id.backButton);

        // Установите обработчик нажатия на кнопку
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();  // Возвращает на предыдущий экран
            }
        });
    }
    private void displayUserData() {
        // Открыть базу данных для чтения
        Cursor cursor = databaseHelper.getUserData(); // Получаем все данные пользователя. Возможно, вам нужно будет сделать запрос для извлечения конкретного пользователя, если у вас есть ID

        // Проверить, если курсор имеет данные
        if (cursor != null && cursor.moveToFirst()) {
            // Извлекаем данные из курсора
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE));
            @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ADDRESS));

            // Устанавливаем данные в TextView
            nameTextView.setText("Name: " + name);
            emailTextView.setText("Email: " + email);
            phoneTextView.setText("Phone: " + phone);
            addressTextView.setText("Address: " + address);

            // Закрыть курсор
            cursor.close();
        }
    }

}