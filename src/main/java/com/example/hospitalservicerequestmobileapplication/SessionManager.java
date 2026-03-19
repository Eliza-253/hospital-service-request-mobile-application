package com.example.hospitalservicerequestmobileapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "session";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void saveUser(int userId, String role) {
        editor.putInt("userId", userId);
        editor.putString("role", role);
        editor.apply();
    }

    public int getUserId() {
        return prefs.getInt("userId", -1);
    }

    public String getRole() {
        return prefs.getString("role", "user");
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }
}
