package com.example.englishdictionary.dictionaryapi;

import com.example.englishdictionary.dictionaryapi.model.RetrieveEntry;
import com.example.englishdictionary.dictionaryapi.model.Wordlist;

public interface OnFetchDataListener {
    void onFetchData(RetrieveEntry retrieveEntry, String message);
    void onError(String message);
}
