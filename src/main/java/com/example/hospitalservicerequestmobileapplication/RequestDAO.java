package com.example.hospitalservicerequestmobileapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class RequestDAO {

    private DatabaseHelper dbHelper;

    public RequestDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addRequest(Request request) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("userId", request.getUserId());
        values.put("serviceId", request.getServiceId());
        values.put("notes", request.getNotes());
        values.put("wardNumber", request.getWardNumber());
        values.put("bedNumber", request.getBedNumber());
        values.put("status", request.getStatus());
        values.put("timestamp", request.getTimestamp());

        return db.insert(DatabaseHelper.TABLE_REQUEST, null, values);
    }

    public ArrayList<String> getAllRequests() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_REQUEST, null, null);

        if (cursor.moveToFirst()) {
            do {
                String req = "ID: " + cursor.getInt(0) + 
                             "\nWard: " + cursor.getString(4) + 
                             " Bed: " + cursor.getString(5) +
                             "\nNotes: " + cursor.getString(3) +
                             "\nStatus: " + cursor.getString(6);
                list.add(req);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
