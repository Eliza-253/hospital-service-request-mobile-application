package com.example.hospitalservicerequestmobileapplication;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteUserActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> users;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        listView = findViewById(R.id.listViewUsers);
        db = new DatabaseHelper(this);

        users = db.getUsers();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, users);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String user = users.get(position);
            db.deleteUser(user);
            Toast.makeText(this, "User Deleted", Toast.LENGTH_SHORT).show();
            users.remove(position);
            adapter.notifyDataSetChanged();
        });
    }
}
