package com.example.englishdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {
    Button btn_cancel_setting;
    private int last_fragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_cancel_setting = (Button) findViewById(R.id.btn_cancel_setting);

        btn_cancel_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);

                intent.putExtra("check_frag",last_fragment);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        last_fragment = intent.getIntExtra("last_fragment", 0);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);

        intent.putExtra("check_frag",last_fragment);
        startActivity(intent);
    }
}