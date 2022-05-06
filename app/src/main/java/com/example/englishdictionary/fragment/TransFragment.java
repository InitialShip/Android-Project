package com.example.englishdictionary.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.widget.AppCompatButton;

import com.example.englishdictionary.ChooseLanguageActivity;
import com.example.englishdictionary.MainActivity;
import com.example.englishdictionary.R;

public class TransFragment extends Fragment {
    Button btn_source, btn_target;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trans, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindButton(view);
    }

    private void bindButton(@NonNull View view) {
        btn_source =(Button) view.findViewById(R.id.btn_source);
        btn_target =(Button) view.findViewById(R.id.btn_target);

        btn_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChooseLanguageActivity.class);
                startActivity(intent);
            }
        });
    }
}