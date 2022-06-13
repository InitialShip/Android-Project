package com.example.englishdictionary.practise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.englishdictionary.practise.Card;
import com.example.englishdictionary.practise.Deck;

import java.util.List;

public class CardAdapter extends ArrayAdapter<Card> {
    private Context context;
    private int resource;
    public CardAdapter(@NonNull Context context, int resource, List<Card> cards) {
        super(context, resource, cards);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Card card = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(this.context).inflate(this.resource,parent,false);


        return convertView;
    }
}
