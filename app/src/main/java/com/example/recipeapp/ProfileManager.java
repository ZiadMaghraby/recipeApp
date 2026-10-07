package com.example.recipeapp;

import android.content.Context;
import android.content.SharedPreferences;

// This class is for manage the user profile data. It saves data permanently on the phone.
public class ProfileManager {

    // I use this name for the file that saves data (SharedPreferences file name).
    private static final String PREF_NAME = "UserProfile";
    // Keys for saving user name and email.
    private static final String KEY_NAME = "UserName";
    private static final String KEY_EMAIL = "UserEmail";

    // Function for saving the user's name and email.
    public static void saveUserData(Context context, String name, String email) {
        // I get the SharedPreferences instance by name.
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit(); // I use the editor to change data.
        editor.putString(KEY_NAME, name); // Save the user name.
        editor.putString(KEY_EMAIL, email); // Save the user email.
        editor.apply(); // Apply makes the changes permanent (asynchronously).
    }

    // Function for getting the user's name.
    public static String getName(Context context) {
        // I get the shared preferences again to read the data.
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        // I return the saved name. If no name found, I return "Chef" as default name.
        return prefs.getString(KEY_NAME, "Chef");
    }

    // Function for getting the user's email.
    public static String getEmail(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        // I return the saved email. I give a default email too.
        return prefs.getString(KEY_EMAIL, "chef@recipeapp.com");
    }

    // Function for deleting all user data (for logout action).
    public static void clearData(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        // I use clear() method to remove all saved data.
        prefs.edit().clear().apply();
    }
}