package com.example.hospitalservicerequestmobileapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboard extends AppCompatActivity {

    Button viewRequests, addService, removeService, deleteUser, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);

        viewRequests = findViewById(R.id.btnViewRequests);
        addService = findViewById(R.id.btnAddService);
        removeService = findViewById(R.id.btnRemoveService);
        deleteUser = findViewById(R.id.btnDeleteUser);
        logout = findViewById(R.id.btnLogout);

        // View Requests
        viewRequests.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, ViewRequest.class);
            startActivity(intent);
        });

        // Add Service
        addService.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, AddServiceActivity.class);
            startActivity(intent);
        });

        // Remove Service
        removeService.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, RemoveActivity.class);
            startActivity(intent);
        });

        // Delete User
        deleteUser.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, DeleteUserActivity.class);
            startActivity(intent);
        });

        // Logout
        logout.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
