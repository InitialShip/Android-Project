package com.example.englishdictionary.settings.datalocal;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharePreferences {
//    String LOCALE_KEY = "locale";
//    String setback_locale ="en";
    String database_name = "database";
    Context context;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;

    public MySharePreferences(Context context) {
        this.context = context;
        this.editor = this.context.getSharedPreferences(database_name, Context.MODE_PRIVATE).edit();
        this.preferences = this.context.getSharedPreferences(database_name, Context.MODE_PRIVATE);
    }

    public String getStringValue(String key) {
        return preferences.getString(key, "");
    }

    public void setStringValue(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public boolean getBooleanValue(String key) {
        return preferences.getBoolean(key, true);
    }

    public void setBooleanValue(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public int getIntValue(String key) {
        return preferences.getInt(key, 1);
    }

    public void setIntValue(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }
}
