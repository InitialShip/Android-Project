package com.example.englishdictionary;

import android.app.Application;

public class MyApplication extends Application {
    private static String current_source = "en";
    private static String current_target = "es";

    public static String getCurrent_source() {
        return current_source;
    }

    public static void setCurrent_source(String current_source) {
        MyApplication.current_source = current_source;
    }

    public static String getCurrent_target() {
        return current_target;
    }

    public static void setCurrent_target(String current_target) {
        MyApplication.current_target = current_target;
    }
}
