package com.example.hospitalservicerequestmobileapplication;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etEmail, etPassword, etConfirm;
    Button btnRegister;

    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirm = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        userDAO = new UserDAO(this);

        btnRegister.setOnClickListener(v -> {
            String u = etUsername.getText().toString();
            String e = etEmail.getText().toString();
            String p = etPassword.getText().toString();
            String c = etConfirm.getText().toString();

            if (!p.equals(c)) {
                Toast.makeText(this, "Passwords mismatch", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User(0, u, e, p, "user");

            if (userDAO.registerUser(user) > 0) {
                Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
