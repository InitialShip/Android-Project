package com.example.englishdictionary.settings.datalocal;

import android.content.Context;

public class DataLocalManager {
    private static DataLocalManager instance;
    private MySharePreferences mySharePreferences;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharePreferences = new MySharePreferences(context);
    }

    public static DataLocalManager getInstance() {
        if(instance == null)
            instance = new DataLocalManager();
        return instance;
    }

    public static void setPrefs(String key, String value) {
        DataLocalManager.getInstance().mySharePreferences.setValueKey(key, value);
    }

    public static String getPrefs(String key) {
        return DataLocalManager.getInstance().mySharePreferences.getValueKey(key);
    }

}
