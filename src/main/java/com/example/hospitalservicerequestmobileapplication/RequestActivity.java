package com.example.hospitalservicerequestmobileapplication;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.*;

public class RequestActivity extends AppCompatActivity {

    Spinner spinnerService;
    EditText etNotes, etWard, etBed;
    Button btnSubmit;

    RequestDAO requestDAO;
    SessionManager session;

    String[] services = {"Cleaning", "Equipment Assistance", "Linen Change", "Porter Services"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        spinnerService = findViewById(R.id.spinnerService);
        etNotes = findViewById(R.id.etNotes);
        etWard = findViewById(R.id.etWard);
        etBed = findViewById(R.id.etBed);
        btnSubmit = findViewById(R.id.btnSubmit);

        requestDAO = new RequestDAO(this);
        session = new SessionManager(this);

        // Spinner setup
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, services);
        spinnerService.setAdapter(adapter);

        btnSubmit.setOnClickListener(v -> {
            String serviceName = spinnerService.getSelectedItem().toString();
            String notes = etNotes.getText().toString();
            String ward = etWard.getText().toString();
            String bed = etBed.getText().toString();

            if (ward.isEmpty() || bed.isEmpty()) {
                Toast.makeText(this, "Enter ward & bed", Toast.LENGTH_SHORT).show();
                return;
            }

            int userId = session.getUserId();

            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    .format(new Date());

            Request request = new Request(
                    0,
                    userId,
                    spinnerService.getSelectedItemPosition(),
                    notes,
                    ward,
                    bed,
                    "Pending",
                    time
            );

            long result = requestDAO.addRequest(request);

            if (result > 0) {
                Toast.makeText(this, "Request submitted", Toast.LENGTH_SHORT).show();
                etNotes.setText("");
                etWard.setText("");
                etBed.setText("");
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
