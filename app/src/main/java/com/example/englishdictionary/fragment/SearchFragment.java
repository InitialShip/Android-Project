package com.example.englishdictionary.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.englishdictionary.R;
import com.example.englishdictionary.adapters.DefinitionAdapter;
import com.example.englishdictionary.dictionaryapi.OnFetchDataListener;
import com.example.englishdictionary.dictionaryapi.RequestManager;
import com.example.englishdictionary.dictionaryapi.model.HeadwordEntry;
import com.example.englishdictionary.dictionaryapi.model.LexicalEntry;
import com.example.englishdictionary.dictionaryapi.model.RetrieveEntry;
import com.example.englishdictionary.dictionarylookup.CategotyEntry;
import com.example.englishdictionary.dictionarylookup.Definition;
import com.example.englishdictionary.dictionarylookup.searchsuggests.DataHelper;
import com.example.englishdictionary.dictionarylookup.searchsuggests.WordSuggest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    AutoCompleteTextView searchView;
    RecyclerView recyclerView;
    FloatingActionButton btnSearch;
    DefinitionAdapter definitionAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = (AutoCompleteTextView) view.findViewById(R.id.search_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        btnSearch = (FloatingActionButton) view.findViewById(R.id.btn_search);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //show word suggest
                getAllWord();
            }
        });

        RequestManager requestManager = new RequestManager(getContext());
        requestManager.getWordMeaning(dataListener, "hello");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestManager requestManager = new RequestManager(getContext());
                requestManager.getWordMeaning(dataListener, searchView.getText().toString());
            }
        });
    }

    private void getAllWord() {
        final List<String> WORDS = new ArrayList<>();
        if (!searchView.getText().toString().equals("")) {
            DataHelper.findSuggestions(getContext(), searchView.getText().toString(), 5
                    , new DataHelper.OnFindWordsListener() {

                @Override
                public void onResults(List<WordSuggest> results) {
                    try {
                        for (WordSuggest w : results) {
                            WORDS.add(w.getWord());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext()
                                , R.layout.search_autocomplete_row, R.id.word_suggest, WORDS);
                        searchView.setAdapter(adapter);
                    } catch (NullPointerException n) {
                        n.printStackTrace();
                    }
                }
            });
        }
    }

    private final OnFetchDataListener dataListener = new OnFetchDataListener() {
        @Override
        public void onFetchData(RetrieveEntry retrieveEntry, String message) {
            if(retrieveEntry == null) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                return;
            }
            showData(retrieveEntry);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(RetrieveEntry retrieveEntry) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<CategotyEntry> categoryEntries = new ArrayList<>();
        List<Definition> definitions = new ArrayList<>();

        List<HeadwordEntry> headwordEntry = retrieveEntry.getResults();
        List<LexicalEntry> lexicalEntries = headwordEntry.get(0).getLexicalEntries();
        for(LexicalEntry l : lexicalEntries) {
            CategotyEntry categotyEntry = new CategotyEntry(l.getText()
                    , l.getLexicalCategory().getText()
                    , l.getEntries().get(0));
            categoryEntries.add(categotyEntry);
        }

        for(CategotyEntry c : categoryEntries) {
            Definition definition = new Definition(c.getCategory()
                    , c.getWord(), c.getEntry()
                    , c.getEntry().getSenses().get(0));
            definitions.add(definition);
        }

        definitionAdapter = new DefinitionAdapter(getContext(), definitions);
        recyclerView.setAdapter(definitionAdapter);
    }
}