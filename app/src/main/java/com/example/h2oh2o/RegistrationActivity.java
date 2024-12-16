package com.example.h2oh2o;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class RegistrationActivity extends AppCompatActivity {
    EditText etFullName, etEmail, etPassword, etPhone, etAddress;
    Button btnRegister;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbHelper = new DatabaseHelper(this);

        etFullName = findViewById(R.id.etfulname);
        etEmail = findViewById(R.id.etemail);
        etPhone = findViewById(R.id.etphone);
        etAddress = findViewById(R.id.etAddress);
        btnRegister = findViewById(R.id.btnreg);



        btnRegister.setOnClickListener(v -> {
            String fullName = etFullName.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();
            String address = etAddress.getText().toString();

            if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            } else {
                boolean isInserted = dbHelper.addUser(fullName, email, phone, address);
                if (isInserted) {
                    Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent( RegistrationActivity.this, Fragment.class);
                    startActivity(intent);

                    finish();
                } else {
                    Toast.makeText(this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}