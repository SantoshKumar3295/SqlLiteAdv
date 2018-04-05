package com.caavo.assignment.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by santo on 2/4/18.
 */

public class SharedPreferenceManager {

    private static SharedPreferences mPreference;
    public static SharedPreferenceManager preferenceManager;

    private SharedPreferenceManager() {
    }

    public static synchronized SharedPreferenceManager getInstance(Context context) {
        if (preferenceManager == null) {
            preferenceManager = new SharedPreferenceManager();
        }
        return preferenceManager;
    }


    public void saveData(String key, String data) {
        mPreference.edit().putString(key, data).apply();
    }

    public void removeData(String key) {
        mPreference.edit().remove(key).apply();
    }

    public void saveData(String key, int data) {
        mPreference.edit().putInt(key, data).apply();
    }

    public void saveData(String key, long data) {
        mPreference.edit().putLong(key, data).apply();
    }

    public void saveData(String key, boolean data) {
        mPreference.edit().putBoolean(key, data).apply();
    }

    public String getString(String key) {
        return mPreference.getString(key, null);
    }

    public int getInt(String key) {
        return mPreference.getInt(key, 0);
    }

    public long getLong(String key) {
        return mPreference.getLong(key, 0);
    }

    public boolean getBoolean(String key) {
        return mPreference.getBoolean(key, false);
    }

    public boolean contains(String key) {
        return mPreference.contains(key);
    }

}
