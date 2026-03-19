package com.example.hospitalservicerequestmobileapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddServiceActivity extends AppCompatActivity {

    EditText etService;
    Button btnAdd;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        etService = findViewById(R.id.etServiceName);
        btnAdd = findViewById(R.id.btnAddService);
        db = new DatabaseHelper(this);

        btnAdd.setOnClickListener(v -> {
            String service = etService.getText().toString();

            if (service.isEmpty()) {
                Toast.makeText(this, "Enter service name", Toast.LENGTH_SHORT).show();
            } else {
                db.addService(service);
                Toast.makeText(this, "Service Added", Toast.LENGTH_SHORT).show();
                etService.setText("");
            }
        });
    }
}
