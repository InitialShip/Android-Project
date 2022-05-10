package com.example.englishdictionary.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishdictionary.R;
import com.example.englishdictionary.dictionarylookup.Phonetic;
import com.example.englishdictionary.dictionarylookup.PhoneticViewHolder;

import java.io.IOException;
import java.util.List;

public class PhoneticAdapter extends RecyclerView.Adapter<PhoneticViewHolder> {
    public Context context;
    public List<Phonetic> phoneticList;

    public PhoneticAdapter(Context context, List<Phonetic> phonetics) {
        this.context = context;
        this.phoneticList = phonetics;
    }

    @NonNull
    @Override
    public PhoneticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhoneticViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.phonetic_row, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PhoneticViewHolder holder
            , @SuppressLint("RecyclerView") int position) {
        holder.word.setText(phoneticList.get(position).getWord() + " ");
        holder.spellWord.setText("[" + phoneticList.get(position).getWordSpelling() + "]");

        holder.playAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer player = new MediaPlayer();
                try {
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource(phoneticList.get(position).getAudioFile());
                    player.prepare();
                    player.start();
                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Couldn't play audio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneticList.size();
    }
}
