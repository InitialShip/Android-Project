package com.example.englishdictionary.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.englishdictionary.R;
import com.example.englishdictionary.practise.Card;
import com.example.englishdictionary.practise.Deck;
import com.example.englishdictionary.practise.PractiseManager;
import com.example.englishdictionary.practise.WordType;
import com.example.englishdictionary.practise.adapter.DeckAdapter;
import com.example.englishdictionary.practise.file.FileHandler;
import com.example.englishdictionary.practise.file.Meta;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PractiseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PractiseFragment extends Fragment {
    private static PractiseManager practiseManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView deckListView;

    public PractiseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PractiseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PractiseFragment newInstance(String param1, String param2) {
        PractiseFragment fragment = new PractiseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        practiseManager = new PractiseManager();
        prepareData();
        generateSample();
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

        deckListView = view.findViewById(R.id.listView_deck);

        DeckAdapter deckAdapter = new DeckAdapter(this.getContext(),R.layout.deck_display_practise,practiseManager.getDecks());

        deckListView.setAdapter(deckAdapter);

        deckListView.setOnItemClickListener(((adapterView, view1, i, l) -> {
            Toast.makeText(this.getContext(),
                    practiseManager.getDecks().get(i).getDeckName() +"\n"+
                            practiseManager.getDecks().get(i).getPrefName() +"\n"+
                            practiseManager.getDecks().get(i).getAmount()
                    ,Toast.LENGTH_SHORT).show();
        }));
    }

    private void prepareData(){
        Meta meta;
        meta = FileHandler.LoadMeta(this.getContext());
        if(meta == null){
            meta = new Meta();
        }
        practiseManager.setMeta(meta);
        List<Deck> decks = new ArrayList<>();
        for (String data: meta.getData()) {
            try{
                decks.add(FileHandler.LoadDeck(this.getContext(),data));
            }catch (Exception e){}
        }
        practiseManager.setDecks(decks);
        if(!decks.isEmpty())
            practiseManager.setSelectedDeck(decks.get(0));
    }
    private void generateSample(){
        if(practiseManager.getMeta() == null){
            practiseManager.setMeta(new Meta());
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
        sampleDeck1.addCard(new Card("Constant","",WordType.NOUN));
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
            Meta meta = practiseManager.getMeta();
            FileHandler.Save(this.getContext(),sampleDeck1,sampleDeck1.getPrefName());
            meta.addData(sampleDeck1.getPrefName());
            FileHandler.Save(this.getContext(),meta, meta.getPrefName());
        }catch (Exception e){

        }

        //Deck sampleDeck2;
    }
}