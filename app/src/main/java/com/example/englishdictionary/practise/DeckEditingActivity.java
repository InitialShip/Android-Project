package com.example.englishdictionary.practise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.englishdictionary.MyApplication;
import com.example.englishdictionary.R;
import com.example.englishdictionary.practise.adapter.CardAdapter;

public class DeckEditingActivity extends AppCompatActivity {
    private EditText edDeckName;
    private TextView tvDeckAmount;
    private TextView tvDeckAverage;
    private TextView tvDeckLatest;
    private ListView cardList;

    private Deck deck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_editing);

        deck = (Deck) getIntent().getSerializableExtra("Selected Deck");

        edDeckName = findViewById(R.id.editText_deckName);
        tvDeckAmount = findViewById(R.id.textView_deckAmount);

        tvDeckLatest = findViewById(R.id.textView_latestPractise);
        cardList = findViewById(R.id.listView_cards);

        edDeckName.setText(deck.getDeckName());
        tvDeckAmount.setText("Amount: " + deck.getAmount());
        tvDeckLatest.setText("Latest Practise: "+deck.getLatestPractise());

        CardAdapter cardAdapter = new CardAdapter(MyApplication.getAppContext(),R.layout.card_display_practise,deck.getCards());
        cardList.setAdapter(cardAdapter);

    }
}