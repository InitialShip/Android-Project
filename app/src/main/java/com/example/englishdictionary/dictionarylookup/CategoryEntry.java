package com.example.englishdictionary.dictionarylookup;


import com.example.englishdictionary.dictionaryapi.model.Entry;

public class CategoryEntry {
    final String word;
    final String category;
    final Entry entry;

    public CategoryEntry(String word, String category, Entry entry) {
        this.word = word;
        this.category = category;
        this.entry = entry;
    }

    public String getWord() {
        return word;
    }

    public String getCategory() {
        return category;
    }

    public Entry getEntry() {
        return entry;
    }
}
