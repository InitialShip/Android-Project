package com.example.englishdictionary.practise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.englishdictionary.MyApplication;
import com.example.englishdictionary.R;
import com.example.englishdictionary.practise.adapter.CardAdapter;
import com.example.englishdictionary.practise.file.FileHandler;
import com.example.englishdictionary.practise.file.Meta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DeckEditingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_editing);

        Deck deck = PractiseManager.getMode().getSelectedDeck();

        EditText edDeckName = findViewById(R.id.editText_deckName);
        TextView tvDeckAmount = findViewById(R.id.textView_deckAmount);

        TextView tvDeckLatest = findViewById(R.id.textView_latestPractise);
        ListView cardList = findViewById(R.id.listView_cards);

        edDeckName.setText(deck.getDeckName());
        tvDeckAmount.setText("Amount: " + deck.getAmount());
        tvDeckLatest.setText("Latest Practise: "+ deck.getLatestPractise());

        CardAdapter cardAdapter = new CardAdapter(MyApplication.getAppContext(),R.layout.card_display_practise, deck.getCards());
        cardList.setAdapter(cardAdapter);

        FloatingActionButton btnConfirm = findViewById(R.id.btn_confirmEdit);
        btnConfirm.setOnClickListener(v->{
            deck.setDeckName(edDeckName.getText().toString());
            setResult(2);
            finish();
        });
        FloatingActionButton btnCancel = findViewById(R.id.btn_cancelEdit);
        btnCancel.setOnClickListener(v->{
            finish();
        });
        FloatingActionButton btnDelete = findViewById(R.id.btn_deleteDeck);
        btnDelete.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.putExtra("Deck Pref",deck.getPrefName());
            setResult(3,intent);
            finish();
        });
        FloatingActionButton btnPractise = findViewById(R.id.btn_startPractise);
        btnPractise.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.putExtra("Deck Pref",deck.getPrefName());
            setResult(4,intent);
            finish();
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}