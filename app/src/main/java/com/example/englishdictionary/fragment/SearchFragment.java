package com.example.englishdictionary.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.englishdictionary.R;
import com.example.englishdictionary.adapters.DefinitionAdapter;
import com.example.englishdictionary.dictionaryapi.OnFetchDataListener;
import com.example.englishdictionary.dictionaryapi.RequestManager;
import com.example.englishdictionary.dictionaryapi.model.HeadwordEntry;
import com.example.englishdictionary.dictionaryapi.model.LexicalEntry;
import com.example.englishdictionary.dictionaryapi.model.RetrieveEntry;
import com.example.englishdictionary.dictionaryapi.model.Sense;
import com.example.englishdictionary.dictionarylookup.CategotyEntry;
import com.example.englishdictionary.dictionarylookup.Definition;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    SearchView mSearchView;

    SearchView searchView;
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

        searchView = (SearchView) view.findViewById(R.id.search_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        btnSearch = (FloatingActionButton) view.findViewById(R.id.btn_search);

        RequestManager requestManager = new RequestManager(getContext());
        requestManager.getWordMeaning(listener, "hello");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RequestManager requestManager = new RequestManager(getContext());
                requestManager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestManager requestManager = new RequestManager(getContext());
                requestManager.getWordMeaning(listener, searchView.getQuery().toString());
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(RetrieveEntry retrieveEntry, String message) {
            if(retrieveEntry == null) {
                Toast.makeText(getContext(), "No search terms", Toast.LENGTH_SHORT).show();
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

        List<CategotyEntry> categotyEntries = new ArrayList<>();
        List<Definition> definitions = new ArrayList<>();

        List<HeadwordEntry> headwordEntry = retrieveEntry.getResults();
        List<LexicalEntry> lexicalEntries = headwordEntry.get(0).getLexicalEntries();
        for(LexicalEntry l : lexicalEntries) {
            CategotyEntry categotyEntry = new CategotyEntry(l.getText()
                    , l.getLexicalCategory().getText()
                    , l.getEntries().get(0));
            categotyEntries.add(categotyEntry);
        }

        for(CategotyEntry c : categotyEntries) {
            Definition definition = new Definition(c.getCategory()
                    , c.getWord(), c.getEntry()
                    , c.getEntry().getSenses().get(0));
            definitions.add(definition);
        }

        definitionAdapter = new DefinitionAdapter(getContext(), definitions);
        recyclerView.setAdapter(definitionAdapter);
    }
}