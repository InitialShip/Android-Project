package com.example.englishdictionary.practise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.englishdictionary.R;
import com.example.englishdictionary.practise.Deck;

import java.util.List;

public class DeckAdapter extends ArrayAdapter<Deck> {
    private Context context;
    private int resource;

    public DeckAdapter(@NonNull Context context, int resource, @NonNull List<Deck> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Deck deck = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(this.context).inflate(this.resource,parent,false);

        TextView deckName = convertView.findViewById(R.id.text_deckName);

        deckName.setText(deck.getDeckName());

        return convertView;
    }
}
