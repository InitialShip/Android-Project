package com.example.englishdictionary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.englishdictionary.R;
import com.example.englishdictionary.dictionarystranlate.LanguageViewHolder;
import com.example.englishdictionary.dictionarystranlate.SubTranslationViewHolder;

public class SubTranslationAdapter extends ArrayAdapter<String> {
    Context context;
    String[] translations;

    public SubTranslationAdapter(@NonNull Context context, String[] translations) {
        super(context, R.layout.sub_translation_item, R.id.sub_translation_item, translations);
        this.translations = translations;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        SubTranslationViewHolder holder = null;

        if(item == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.sub_translation_item, parent, false);
            holder = new SubTranslationViewHolder(item);
            item.setTag(holder);
        }
        else {
            holder = (SubTranslationViewHolder) item.getTag();
        }

        holder.sub_translation_item.setText(this.translations[position]);

        return item;
    }
}
