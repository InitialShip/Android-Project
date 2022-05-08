package com.example.englishdictionary.dictionarylookup;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishdictionary.R;

public class PhoneticViewHolder extends RecyclerView.ViewHolder {
    public TextView word;
    public TextView spellWord;
    public ImageView playAudio;

    public PhoneticViewHolder(@NonNull View itemView) {
        super(itemView);

        word = (TextView) itemView.findViewById(R.id.word_spelling);
        spellWord = (TextView) itemView.findViewById(R.id.spelling);
        playAudio = (ImageView) itemView.findViewById(R.id.play_audio);
    }
}
