package com.example.hospitalservicerequestmobileapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hospital.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_USER = "users";
    public static final String TABLE_SERVICE = "services";
    public static final String TABLE_REQUEST = "requests";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS = "CREATE TABLE " + TABLE_USER + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "email TEXT," +
                "password TEXT," +
                "role TEXT)";

        String CREATE_SERVICES = "CREATE TABLE " + TABLE_SERVICE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "code TEXT," +
                "name TEXT)";

        String CREATE_REQUESTS = "CREATE TABLE " + TABLE_REQUEST + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userId INTEGER," +
                "serviceId INTEGER," +
                "notes TEXT," +
                "wardNumber TEXT," +
                "bedNumber TEXT," +
                "status TEXT," +
                "timestamp TEXT)";

        db.execSQL(CREATE_USERS);
        db.execSQL(CREATE_SERVICES);
        db.execSQL(CREATE_REQUESTS);

        // Insert default admin user
        ContentValues values = new ContentValues();
        values.put("username", "admin");
        values.put("email", "admin@hospital.com");
        values.put("password", "admin123");
        values.put("role", "admin");
        db.insert(TABLE_USER, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUEST);
        onCreate(db);
    }

    // Add Service
    public void addService(String serviceName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", serviceName);
        db.insert(TABLE_SERVICE, null, values);
        db.close();
    }

    // Get all users (usernames)
    public ArrayList<String> getUsers() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT username FROM " + TABLE_USER, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // Delete User
    public void deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, "username=?", new String[]{username});
        db.close();
    }

    // Get all services (names)
    public ArrayList<String> getServices() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM " + TABLE_SERVICE, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // Delete Service
    public void deleteService(String serviceName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SERVICE, "name=?", new String[]{serviceName});
        db.close();
    }

    // Get all requests
    public ArrayList<String> getAllRequests() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REQUEST, null);
        if (cursor.moveToFirst()) {
            do {
                String request = "Ward: " + cursor.getString(4) + 
                                 ", Bed: " + cursor.getString(5) + 
                                 "\nNotes: " + cursor.getString(3);
                list.add(request);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
