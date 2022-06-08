package com.example.englishdictionary.practise.mode;

import com.example.englishdictionary.practise.Deck;

public abstract class PractiseMode {
    protected String name;
    protected String description;
    protected Deck selectedDeck;
    public PractiseMode(){}

    public void setDeck(Deck newDeck){
        selectedDeck = newDeck;
    }
    public String getDescription(){
        return description;
    }
    public abstract String getQuestion();
    public abstract String[] getAnswers();
}
