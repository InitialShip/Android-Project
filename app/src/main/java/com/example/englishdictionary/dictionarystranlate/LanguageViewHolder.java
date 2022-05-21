package com.example.englishdictionary.dictionarystranlate;

import android.view.View;
import android.widget.TextView;

import com.example.englishdictionary.R;

public class LanguageViewHolder {
    public TextView tv_language;

    public LanguageViewHolder(View v) {
        tv_language = v.findViewById(R.id.tv_language);
    }
}
