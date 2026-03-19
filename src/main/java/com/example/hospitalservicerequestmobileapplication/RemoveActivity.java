package com.example.hospitalservicerequestmobileapplication;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RemoveActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> services;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_service);

        listView = findViewById(R.id.listViewServices);
        db = new DatabaseHelper(this);

        services = db.getServices();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, services);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String service = services.get(position);
            db.deleteService(service);
            Toast.makeText(this, "Service Removed", Toast.LENGTH_SHORT).show();
            services.remove(position);
            adapter.notifyDataSetChanged();
        });
    }
}
