package com.example.hospitalservicerequestmobileapplication; // FIXED: Matches your file path

import android.os.Bundle;
import android.view.View;
import android.widget.*; // Imports Spinner, Button, EditText, etc.
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    // Define the UI elements from your activity_admin.xml
    private Spinner spinnerService;
    private EditText etWard, etBed, etNotes;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // 1. Initialize the views
        spinnerService = findViewById(R.id.spinnerService);
        etWard = findViewById(R.id.etWard);
        etBed = findViewById(R.id.etBed);
        etNotes = findViewById(R.id.etNotes);
        btnSubmit = findViewById(R.id.btnSubmit);

        // 2. Optional: Populate the Spinner with some data
        String[] services = {"Cleaning", "Maintenance", "Emergency", "Medication"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, services);
        spinnerService.setAdapter(adapter);

        // 3. Set a click listener for the Submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRequest();
            }
        });
    }

    private void submitRequest() {
        String service = spinnerService.getSelectedItem().toString();
        String ward = etWard.getText().toString().trim();
        String bed = etBed.getText().toString().trim();

        if (ward.isEmpty() || bed.isEmpty()) {
            Toast.makeText(this, "Please fill in Ward and Bed info", Toast.LENGTH_SHORT).show();
        } else {
            // Logic to save data would go here
            Toast.makeText(this, "Request submitted for " + service, Toast.LENGTH_SHORT).show();
        }
    }
}