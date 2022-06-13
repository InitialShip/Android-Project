package com.example.englishdictionary.practise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishdictionary.R;
import com.example.englishdictionary.practise.file.FileHandler;
import com.example.englishdictionary.practise.mode.PractiseMode;

import java.util.List;

public class PractiseActivity extends AppCompatActivity implements View.OnClickListener {
    private static PractiseMode mode;

    TextView textQuestion;
    Button btnAn1, btnAn2, btnAn3, btnAn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practise);

        mode = PractiseManager.getMode();
        mode.enter();
        textQuestion = findViewById(R.id.text_question);
        btnAn1 = findViewById(R.id.btn_answer1);
        btnAn2 = findViewById(R.id.btn_answer2);
        btnAn3 = findViewById(R.id.btn_answer3);
        btnAn4 = findViewById(R.id.btn_answer4);

        btnAn1.setOnClickListener(this);
        btnAn2.setOnClickListener(this);
        btnAn3.setOnClickListener(this);
        btnAn4.setOnClickListener(this);

        initializeData();
    }
    private void initializeData(){
        btnAn1.setEnabled(true);
        btnAn2.setEnabled(true);
        btnAn3.setEnabled(true);
        btnAn4.setEnabled(true);

        textQuestion.setText(mode.getQuestion());
        List<String> answer = mode.getAnswers();
        btnAn1.setText(answer.get(0));
        btnAn2.setText(answer.get(1));
        btnAn3.setText(answer.get(2));
        btnAn4.setText(answer.get(3));
    }

    @Override
    public void onClick(View view) {
        btnAn1.setEnabled(false);
        btnAn2.setEnabled(false);
        btnAn3.setEnabled(false);
        btnAn4.setEnabled(false);

        Button btn = findViewById(view.getId());

        if(mode.inputAnswer(btn.getText().toString())){
            Toast.makeText(this,"Right",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Wrong",Toast.LENGTH_SHORT).show();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(mode.isEndOfDeck()){
                setResult(2);
                finish();
            }
            initializeData();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(2);
        finish();
        super.onBackPressed();
    }
}