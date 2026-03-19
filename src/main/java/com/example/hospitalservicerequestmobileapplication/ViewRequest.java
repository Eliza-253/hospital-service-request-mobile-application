package com.example.hospitalservicerequestmobileapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ViewRequest extends AppCompatActivity {

    ListView listView;
    ArrayList<String> requests;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);

        listView = findViewById(R.id.listViewRequests);
        db = new DatabaseHelper(this);

        requests = db.getAllRequests();

        listView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                requests
        ));
    }
}
