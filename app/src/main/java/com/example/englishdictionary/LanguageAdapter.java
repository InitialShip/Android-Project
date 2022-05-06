package com.example.englishdictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LanguageAdapter extends ArrayAdapter<String> {
    Context context;
    String[] lan;
    String[] display;

    public LanguageAdapter(@NonNull Context context, String[] lan, String[] display) {
        super(context, R.layout.language_item, R.id.tv_language, lan);
        this.context = context;
        this.lan = lan;
        this.display = display;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        LanguageViewHolder languageViewHolder = null;

        if(item == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.language_item, parent, false);
            languageViewHolder = new LanguageViewHolder(item);
            item.setTag(languageViewHolder);
        }
        else {
            languageViewHolder = (LanguageViewHolder) item.getTag();
        }

        languageViewHolder.tv_language.setText(display[position]);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "You Clicked" + display[position], Toast.LENGTH_SHORT).show();
            }
        });

        return item;
    }
}
