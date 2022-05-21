package com.example.englishdictionary.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.englishdictionary.MainActivity;
import com.example.englishdictionary.R;
import com.example.englishdictionary.dictionarystranlate.LanguageViewHolder;

public class LanguageAdapter extends ArrayAdapter<String> {
    Context context;
    String[] lan;
    String[] display;
    int check;

    public LanguageAdapter(@NonNull Context context, String[] lan, String[] display, int check) {
        super(context, R.layout.language_item, R.id.tv_language, lan);
        this.context = context;
        this.lan = lan;
        this.display = display;
        this.check = check;
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
                Intent intent = new Intent(context, MainActivity.class);

                intent.putExtra("check_frag", 1);
                intent.putExtra("lang", lan[position]);

                if (check == 1) {
                    intent.putExtra("check_btn", 1);
                }
                else {
                    intent.putExtra("check_btn", 0);
                }
                ((Activity)context).finish();
                context.startActivity(intent);
            }
        });

        return item;
    }
}
