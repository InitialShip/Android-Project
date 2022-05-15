package com.example.englishdictionary.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishdictionary.ChooseLanguageActivity;
import com.example.englishdictionary.adapters.SubTranslationAdapter;
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
import java.util.zip.Inflater;

public class TransFragment extends Fragment {
    Button btn_source, btn_target, btn_sound, btn_cancel_trans;
    EditText source_text;
    ViewStub sound_exit_stub, main_translation_stub, sub_translation_stub;
    TextView translation_content, target_lang_trans;
    ListView sub_translation_list;
    int TRANSLATION_STATUS = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater out = TransitionInflater.from(requireContext());
        setExitTransition(out.inflateTransition(R.transition.fade));

        TransitionInflater in = TransitionInflater.from(requireContext());
        setEnterTransition(in.inflateTransition(R.transition.slide_right));
    }

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
        sound_exit_stub = view.findViewById(R.id.stub_sound_exit);
        main_translation_stub = view.findViewById(R.id.main_translation_stub);
        sub_translation_stub = view.findViewById(R.id.sub_translation_stub);

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

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode == KeyEvent.KEYCODE_BACK
                        && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                    onBackPressed();
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
        TRANSLATION_STATUS = 1;

        sound_exit_stub.setVisibility(View.VISIBLE);
        main_translation_stub.setVisibility(View.VISIBLE);
        sub_translation_stub.setVisibility(View.VISIBLE);

        btn_sound = (Button) view.findViewById(R.id.btn_sound);
        btn_cancel_trans = (Button) view.findViewById(R.id.btn_cancel_trans);
        translation_content = view.findViewById(R.id.translation_content);
        target_lang_trans = view.findViewById(R.id.target_lang_trans);
        sub_translation_list = view.findViewById(R.id.list_sub_translation);

        btn_cancel_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                source_text.setText(null);
                sound_exit_stub.setVisibility(View.GONE);
                main_translation_stub.setVisibility(View.GONE);
                sub_translation_stub.setVisibility(View.GONE);
            }
        });

        //request translate
        RequestManager requestManager = new RequestManager(getContext());
        requestManager.getTranslate(listener,MyApplication.getCurrent_source()
                ,MyApplication.getCurrent_target(), source_text.getText().toString());
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

        List<String> sub_trans = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext()
                , R.layout.sub_translation_item, R.id.sub_translation_item, sub_trans);

        List<HeadwordEntry> headwordEntry = retrieveEntry.getResults();
        LexicalEntry lexicalEntries = headwordEntry.get(0).getLexicalEntries().get(0);
        Entry entry = lexicalEntries.getEntries().get(0);
        List<Sense> senses = entry.getSenses();
        TranslationsList translationsList = new TranslationsList();

        try {
            for (Sense s : senses) {
                if (s.getTranslations() == null) {
                    if (s.getSubsenses().size() > 0)
                        translationsList.addAll(s.getSubsenses().get(0).getTranslations());
                } else
                    translationsList.addAll(s.getTranslations());
            }
            if (translationsList.size() > 0) {
                for (int i = 0; i < Languages.getLans().length; i++) {
                    if (translationsList.get(0).getLanguage().equals(Languages.getLans()[i]))
                        target_lang_trans.setText(Languages.getDisplays()[i]);
                }

                translation_content.setText(translationsList.get(0).getText());
                translationsList.remove(0);

                int limit = 0;
                for (TranslationsListInner t : translationsList) {
                    limit++;
                    sub_trans.add(t.getText());
                    if (limit >= 4)
                        break;
                }

                sub_translation_list.setAdapter(adapter);
                setListViewHeightBasedOnItems(sub_translation_list);
            }
            else {
                adapter.notifyDataSetChanged();
                setListViewHeightBasedOnItems(sub_translation_list);
                translation_content.setText("No available translation");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    void onBackPressed() {
        if (TRANSLATION_STATUS == 0) {
            System.exit(0);
        }
        else {
            source_text.setText(null);
            sound_exit_stub.setVisibility(View.GONE);
            main_translation_stub.setVisibility(View.GONE);
            sub_translation_stub.setVisibility(View.GONE);
        }
    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
}