package com.example.englishdictionary.dictionaryapi;

import com.example.englishdictionary.dictionaryapi.model.Wordlist;

public interface OnFetchSearchDataListener {
    void onFetchData(Wordlist wordlist, String message);
    void onError(String message);
}
