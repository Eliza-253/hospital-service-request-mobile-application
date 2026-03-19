package com.example.hospitalservicerequestmobileapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    TextView txtRegister;

    UserDAO userDAO;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);

        userDAO = new UserDAO(this);
        session = new SessionManager(this);

        // LOGIN BUTTON
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = userDAO.login(username, password);

            if (user != null) {
                session.saveUser(user.getId(), user.getRole());

                // FIX: Redirect admin to AdminDashboard instead of AdminActivity
                if (user.getRole().equalsIgnoreCase("admin")) {
                    startActivity(new Intent(this, AdminDashboard.class));
                } else {
                    startActivity(new Intent(this, RequestActivity.class));
                }

                finish();
            } else {
                Toast.makeText(this, "Invalid login", Toast.LENGTH_SHORT).show();
            }
        });

        // GO TO REGISTER
        txtRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class))
        );
    }
}
