package com.example.englishdictionary.practise.mode;

import com.example.englishdictionary.practise.Deck;

public abstract class PractiseMode {
    public String Name;
    public String Description;
    public Deck SelectedDeck;

    public abstract String getQuestion();
    public abstract String[] getAnswers();
}
