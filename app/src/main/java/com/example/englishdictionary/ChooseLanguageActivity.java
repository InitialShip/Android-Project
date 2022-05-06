package com.example.englishdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ChooseLanguageActivity extends AppCompatActivity {

    ListView lv_languages;
    String[] lans = {"en", "ar", "zh", "fa", "fr", "ka", "de", "el", "gu", "ha", "hi", "ig", "id",
            "xh", "zu", "it", "lv", "ms", "mr", "nso", "pt", "qu", "ro", "ru", "tn", "es", "sw"
            ,"tg", "ta", "tt", "te", "tpi", "tk", "ur", "yo"};
    String[] displays = {"English", "Arabic", "Chinese", "Farsi", "French", "Georgian", "German"
            , "Greek", "Gujarati", "Hausa", "Hindi", "Igbo", "Indonesia", "isiXhosa", "isiZulu"
            , "Italian", "Latvian", "Malay", "Marathi", "Northern Sotho", "Portuguese", "Quechua"
            , "Romanian", "Russian", "Setswana", "Spanish", "Swahili", "Tajik", "Tamil", "Tatar"
            , "Telugu", "Tok Pisin", "Turkmen", "Urdu", "Yoruba"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);

        lv_languages = findViewById(R.id.lv_languages);
        LanguageAdapter languageAdapter = new LanguageAdapter(this, lans, displays);
        lv_languages.setAdapter(languageAdapter);

    }
}