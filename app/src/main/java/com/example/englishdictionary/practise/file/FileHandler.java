package com.example.englishdictionary.practise.file;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.englishdictionary.practise.Deck;
import com.google.gson.Gson;

public class FileHandler {
    public static void Save(@NonNull Context context, Object object,String name){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(name,json);
        editor.apply();
    }
    public static Deck LoadDeck(@NonNull Context context,String name){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(name, null);
        return gson.fromJson(json, Deck.class);
    }
    public static Meta LoadMeta(@NonNull Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("meta", null);
        return gson.fromJson(json, Meta.class);
    }
    public static void Delete(@NonNull Context context,String name){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(name).apply();

    }
}
