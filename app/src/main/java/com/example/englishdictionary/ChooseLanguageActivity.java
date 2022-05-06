package com.example.englishdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ChooseLanguageActivity extends AppCompatActivity {

    ListView lv_languages;
    String[] lans = Languages.getLans();
    String[] displays = Languages.getDisplays();
    Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);

        lv_languages = findViewById(R.id.lv_languages);
        btn_cancel = findViewById(R.id.btn_cancel_choose);

        Intent intent = getIntent();

        LanguageAdapter languageAdapter = new LanguageAdapter(this, lans
                , displays, intent.getIntExtra("check_btn", 0));
        lv_languages.setAdapter(languageAdapter);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseLanguageActivity.this, MainActivity.class);

                intent.putExtra("check_frag",1);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChooseLanguageActivity.this, MainActivity.class);

        intent.putExtra("check_frag",1);
        startActivity(intent);
    }
}