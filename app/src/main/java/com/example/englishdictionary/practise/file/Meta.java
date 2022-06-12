package com.example.englishdictionary.practise.file;

import java.util.ArrayList;
import java.util.List;

//Contains deck preference name
public class Meta {
    private List<String> data;
    private final String prefName;

    public Meta(){
        this.prefName = "meta";
        data = new ArrayList<>();
    }

    public List<String> getData() {
        return data;
    }

    public String getPrefName(){
        return prefName;
    }

    public void addData(String data){
        this.data.add(data);
    }
}
