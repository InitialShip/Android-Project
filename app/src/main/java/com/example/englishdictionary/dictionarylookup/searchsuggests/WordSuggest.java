package com.example.englishdictionary.dictionarylookup.searchsuggests;

public class WordSuggest {
    public String word;

    public WordSuggest(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return this.getWord();
    }
}
