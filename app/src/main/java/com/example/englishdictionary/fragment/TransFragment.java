package com.example.englishdictionary.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishdictionary.ChooseLanguageActivity;
import com.example.englishdictionary.dictionaryapi.OnFetchDataListener;
import com.example.englishdictionary.dictionaryapi.RequestManager;
import com.example.englishdictionary.dictionaryapi.model.Entry;
import com.example.englishdictionary.dictionaryapi.model.HeadwordEntry;
import com.example.englishdictionary.dictionaryapi.model.LexicalEntry;
import com.example.englishdictionary.dictionaryapi.model.RetrieveEntry;
import com.example.englishdictionary.dictionaryapi.model.Sense;
import com.example.englishdictionary.dictionaryapi.model.TranslationsList;
import com.example.englishdictionary.dictionaryapi.model.TranslationsListInner;
import com.example.englishdictionary.dictionarylookup.CategotyEntry;
import com.example.englishdictionary.dictionarystranlate.Languages;
import com.example.englishdictionary.MyApplication;
import com.example.englishdictionary.R;
import com.example.englishdictionary.dictionarystranlate.Translation;

import java.util.ArrayList;
import java.util.List;

public class TransFragment extends Fragment {
    Button btn_source, btn_target, btn_sound, btn_cancel_trans;
    EditText source_text;
    ViewStub viewStub;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trans, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bind(view);
    }

    private void bind(@NonNull View view) {
        btn_source = (Button) view.findViewById(R.id.btn_source);
        btn_target = (Button) view.findViewById(R.id.btn_target);
        source_text = (EditText) view.findViewById(R.id.source_text);
        source_text.setImeOptions(EditorInfo.IME_ACTION_GO);
        viewStub = view.findViewById(R.id.stub);

        String source_name = Languages.getDisplayByShort(MyApplication.getCurrent_source());
        String target_name = Languages.getDisplayByShort(MyApplication.getCurrent_target());

        btn_source.setText(source_name);
        btn_target.setText(target_name);

        btn_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChooseLanguageActivity.class);

                intent.putExtra("check_btn", 0);
                startActivity(intent);
            }
        });

        btn_target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChooseLanguageActivity.class);

                intent.putExtra("check_btn", 1);
                startActivity(intent);
            }
        });

        source_text.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                if (actionId == EditorInfo.IME_ACTION_GO) {

                    source_text.clearFocus();

                    closeKeyboard(view);

                    //reveal View stub
                    showViewStub(view);

                    //request translate
                    RequestManager requestManager = new RequestManager(getContext());
                    requestManager.getTranslate(listener,MyApplication.getCurrent_source()
                            ,MyApplication.getCurrent_target(), source_text.getText().toString());

                    return true;
                }
                return false;
            }

        });

        source_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View textview, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    source_text.clearFocus();
                    closeKeyboard(view);

                    showViewStub(view);

                    return true;
                }
                return false;
            }
        });
    }

    void closeKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    void showViewStub(View view) {

        viewStub.setVisibility(View.VISIBLE);
        btn_sound = (Button) view.findViewById(R.id.btn_sound);
        btn_cancel_trans = (Button) view.findViewById(R.id.btn_cancel_trans);
        btn_cancel_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                source_text.setText(null);
                viewStub.setVisibility(View.GONE);
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

    void showData(RetrieveEntry retrieveEntry) {
        List<CategotyEntry> categotyEntries = new ArrayList<>();
        List<Translation> translations = new ArrayList<>();

        List<HeadwordEntry> headwordEntry = retrieveEntry.getResults();
        LexicalEntry lexicalEntries = headwordEntry.get(0).getLexicalEntries().get(0);
        Entry entry = lexicalEntries.getEntries().get(0);
        List<Sense> senses = entry.getSenses();
        TranslationsList translationsList = new TranslationsList();

        for (Sense s : senses) {
            translationsList.addAll(s.getTranslations());
        }

        for (TranslationsListInner t : translationsList) {
            Log.v("Translation", t.getLanguage() +": " + t.getText());
        }

    }
}