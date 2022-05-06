package com.example.englishdictionary;

import android.view.View;
import android.widget.TextView;

public class LanguageViewHolder {
    TextView tv_language;

    LanguageViewHolder(View v) {
        tv_language = v.findViewById(R.id.tv_language);
    }
}
