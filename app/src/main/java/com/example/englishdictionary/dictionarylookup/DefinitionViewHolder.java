package com.example.englishdictionary.dictionarylookup;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishdictionary.R;

public class DefinitionViewHolder extends RecyclerView.ViewHolder {

    public TextView category;
    public TextView word;
    public TextView etymology;
    public TextView meaning;

    public DefinitionViewHolder(@NonNull View itemView) {
        super(itemView);

        category = (TextView) itemView.findViewById(R.id.category);
        word = (TextView) itemView.findViewById(R.id.word);
        etymology = (TextView) itemView.findViewById(R.id.etymology);
        meaning = (TextView) itemView.findViewById(R.id.definition);
    }
}
