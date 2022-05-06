package com.example.englishdictionary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishdictionary.R;
import com.example.englishdictionary.dictionarylookup.Definition;
import com.example.englishdictionary.dictionarylookup.DefinitionViewHolder;

import java.util.List;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionViewHolder> {
    private Context context;
    List<Definition> definitionList;

    public DefinitionAdapter(Context context, List<Definition> definitionList) {
        this.context = context;
        this.definitionList = definitionList;
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DefinitionViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.definiton_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        holder.category.setText(definitionList.get(position).getCategory());
        holder.word.setText(definitionList.get(position).getWord());
        holder.etymology.setText(definitionList.get(position).getEtymology());
        holder.meaning.setText(definitionList.get(position).getDefiniton());
    }

    @Override
    public int getItemCount() {
        return definitionList.size();
    }
}
