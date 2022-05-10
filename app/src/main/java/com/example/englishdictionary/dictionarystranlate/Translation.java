package com.example.englishdictionary.dictionarystranlate;

import com.example.englishdictionary.dictionaryapi.model.Entry;

public class Translation {
    private String language;
    private String text;
    private String type;

    public Translation(String language, String text, String type) {
        this.language = language;
        this.text = text;
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
