package com.example.englishdictionary.dictionarystranlate;

import android.view.View;
import android.widget.TextView;

import com.example.englishdictionary.R;

public class SubTranslationViewHolder {
    public TextView sub_translation_item;

    public SubTranslationViewHolder(View v) {
        sub_translation_item = v.findViewById(R.id.sub_translation_item);
    }
}
