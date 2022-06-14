package com.example.englishdictionary.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.englishdictionary.MainActivity;
import com.example.englishdictionary.MyApplication;
import com.example.englishdictionary.R;
import com.example.englishdictionary.practise.Card;
import com.example.englishdictionary.practise.Deck;
import com.example.englishdictionary.practise.DeckEditingActivity;
import com.example.englishdictionary.practise.PractiseActivity;
import com.example.englishdictionary.practise.PractiseManager;
import com.example.englishdictionary.practise.WordType;
import com.example.englishdictionary.practise.adapter.DeckAdapter;
import com.example.englishdictionary.practise.file.FileHandler;
import com.example.englishdictionary.practise.file.Meta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class PractiseFragment extends Fragment {
    private static PractiseManager practiseManager;


    public PractiseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        practiseManager = new PractiseManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_practise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prepareData();

        ListView deckListView = view.findViewById(R.id.listView_deck);
        FloatingActionButton btnAddDeck = view.findViewById(R.id.btn_addDeck);
        practiseManager.getDecks().sort(Deck::compare);
        DeckAdapter deckAdapter = new DeckAdapter(MyApplication.getAppContext(),R.layout.deck_display_practise,practiseManager.getDecks());

        deckListView.setAdapter(deckAdapter);

        deckListView.setOnItemClickListener(((adapterView, view1, i, l) -> {
            Intent intent = new Intent(getActivity(), DeckEditingActivity.class);
            practiseManager.setSelectedDeck(practiseManager.getDecks().get(i));

            startActivityForResult(intent,1);
        }));
        btnAddDeck.setOnClickListener(v->{

        });
        FloatingActionButton btnAddSample = view.findViewById(R.id.btn_addSampleDeck);
        btnAddSample.setOnClickListener(v->{
            generateSample();
            reload();
        });
    }

    private void prepareData(){
        Meta meta;
        meta = FileHandler.LoadMeta(MyApplication.getAppContext());
        if(meta == null){
            meta = new Meta();
        }
        PractiseManager.setMeta(meta);
        List<Deck> decks = new ArrayList<>();
        for (String data: meta.getData()) {
            try{
                decks.add(FileHandler.LoadDeck(MyApplication.getAppContext(),data));
            }catch (Exception e){}
        }
        practiseManager.setDecks(decks);
        if(!decks.isEmpty())
            practiseManager.setSelectedDeck(decks.get(0));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //1 add
        //2 save
        //3 delete
        //4 practise
        switch (resultCode){
            case 2:
                FileHandler.Save(MyApplication.getAppContext(),practiseManager.getSelectedDeck(),practiseManager.getSelectedDeck().getPrefName());
                reload();
                break;
            case 3:
                String name = data.getStringExtra("Deck Pref");
                PractiseManager.getMeta().remove(name);
                FileHandler.Save(MyApplication.getAppContext(),PractiseManager.getMeta(),PractiseManager.getMeta().getPrefName());
                FileHandler.Delete(MyApplication.getAppContext(),name);
                reload();
                break;
            case 4:
                Intent intent = new Intent(getActivity(), PractiseActivity.class);
                startActivityForResult(intent,1);
                break;
        }
    }
    private void reload(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,new PractiseFragment());
        fragmentTransaction.commit();
    }

    private void generateSample(){
        if(PractiseManager.getMeta() == null){
            PractiseManager.setMeta(new Meta());
        }
        Deck sampleDeck1 = new Deck("Programing vocabulary");
        sampleDeck1.addCard(new Card("Algorithm","Thuật toán",WordType.NOUN));
        sampleDeck1.addCard(new Card("Argument","Đối số",WordType.NOUN));
        sampleDeck1.addCard(new Card("Array","Mảng",WordType.NOUN));
        sampleDeck1.addCard(new Card("Assignment","Gáng giá trị",WordType.NOUN));
        sampleDeck1.addCard(new Card("Brackets","Dấu ngoặc",WordType.NOUN));
        sampleDeck1.addCard(new Card("Bug","Lỗi",WordType.NOUN));
        sampleDeck1.addCard(new Card("Call","chạy lệnh trong hàm",WordType.VERB));
        sampleDeck1.addCard(new Card("Class","Lớp đối tượng",WordType.NOUN));
        sampleDeck1.addCard(new Card("Comment","Chú thích",WordType.NOUN));
        sampleDeck1.addCard(new Card("Comment out","Chuyển lệnh thành chú thích",WordType.VERB));
        sampleDeck1.addCard(new Card("Compiler","Trình biên dịch",WordType.NOUN));
        sampleDeck1.addCard(new Card("Constant","Hằng số",WordType.NOUN));
        sampleDeck1.addCard(new Card("Crash","Dừng chương trình vì lỗi",WordType.VERB));
        sampleDeck1.addCard(new Card("Data structure","Cấu trúc dữ liệu",WordType.NOUN));
        sampleDeck1.addCard(new Card("Debug","Gỡ lỗi",WordType.VERB));
        sampleDeck1.addCard(new Card("Executable","Chương trình có thể chạy",WordType.NOUN));
        sampleDeck1.addCard(new Card("Function","Hàm",WordType.NOUN));
        sampleDeck1.addCard(new Card("Function call","Gọi hàm",WordType.VERB));
        sampleDeck1.addCard(new Card("Implement","hiện thực hóa",WordType.VERB));
        sampleDeck1.addCard(new Card("Instance","Một đối tượng",WordType.NOUN));
        sampleDeck1.addCard(new Card("Loop","Vòng lặp",WordType.NOUN));
        sampleDeck1.addCard(new Card("Method","Phương thức",WordType.NOUN));
        sampleDeck1.addCard(new Card("Nested","Lồng vào nhau",WordType.ADJECTIVE));
        sampleDeck1.addCard(new Card("Object","Đối tượng",WordType.NOUN));
        sampleDeck1.addCard(new Card("Object-oriented","Hướng đối tượng",WordType.ADJECTIVE));
        sampleDeck1.addCard(new Card("Procedure","Thủ tục",WordType.NOUN));
        sampleDeck1.addCard(new Card("Read","Đọc dữ liệu",WordType.VERB));
        sampleDeck1.addCard(new Card("Return","Trả về",WordType.VERB));
        sampleDeck1.addCard(new Card("Return value","Giá trị trả về",WordType.NOUN));
        sampleDeck1.addCard(new Card("Run","Chạy chương trình",WordType.VERB));
        sampleDeck1.addCard(new Card("String","Chuổi",WordType.NOUN));
        sampleDeck1.addCard(new Card("Syntax","Cú pháp",WordType.NOUN));
        sampleDeck1.addCard(new Card("Type","Kiểu dữ liệu",WordType.NOUN));
        sampleDeck1.addCard(new Card("Value","Giá trị",WordType.NOUN));
        sampleDeck1.addCard(new Card("Variable","Biến",WordType.NOUN));
        sampleDeck1.addCard(new Card("Write","Ghi dữ liệu",WordType.NOUN));
        //add to meta


        try{
            Meta meta = PractiseManager.getMeta();
            FileHandler.Save(MyApplication.getAppContext(),sampleDeck1,sampleDeck1.getPrefName());
            meta.addData(sampleDeck1.getPrefName());
            FileHandler.Save(MyApplication.getAppContext(),meta, meta.getPrefName());
        }catch (Exception e){

        }
        //Deck sampleDeck2;
    }
}