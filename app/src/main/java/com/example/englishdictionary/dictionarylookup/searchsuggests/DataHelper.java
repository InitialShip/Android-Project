package com.example.englishdictionary.dictionarylookup.searchsuggests;

import android.content.Context;
import android.widget.Filter;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.englishdictionary.dictionaryapi.OnFetchSearchDataListener;
import com.example.englishdictionary.dictionaryapi.RequestManager;
import com.example.englishdictionary.dictionaryapi.model.Wordlist;
import com.example.englishdictionary.dictionaryapi.model.WordlistResults;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {
    private static List<WordSuggest> wordSuggestData;

    public interface OnFindWordsListener {
        void onResults(List<WordSuggest> results);
    }

    public static void findSuggestions(Context context, String query, final int limit
                                       , final OnFindWordsListener listener) {
        initWordSuggestList(context, query);
        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                List<WordSuggest> suggestionList = new ArrayList<>();
                if (!(constraint == null || constraint.length() == 0)) {
                    suggestionList.addAll(wordSuggestData);
                }

                FilterResults results = new FilterResults();
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<WordSuggest>) results.values);
                }
            }
        }.filter(query);

    }

    public static void initWordSuggestList(Context context, String query) {
        OnFetchSearchDataListener listener = new OnFetchSearchDataListener() {
            @Override
            public void onFetchData(Wordlist wordlist, String message) {
                if(wordlist == null) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    return;
                }
                getWordSuggest(wordlist);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        };

        RequestManager requestManager = new RequestManager(context);
        requestManager.getWordSearch(listener, query);
    }

    public static void getWordSuggest(Wordlist wordlist) {
        wordSuggestData = new ArrayList<>();
        List<WordlistResults> wordlistResults = wordlist.getResults();
        for(WordlistResults wr : wordlistResults) {
            WordSuggest wordSuggest = new WordSuggest(wr.getWord());

            wordSuggestData.add(wordSuggest);
        }
    }
}
