package com.example.englishdictionary.practise.mode;

import java.util.List;

public class Reverb extends PractiseMode{
    public Reverb(){
        this.name = "Reverb";
        this.description = "Reverb Mode"; //TODO change
    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public String getQuestion() {
        return null;
    }

    @Override
    public List<String> getAnswers() {
        return null;
    }

    @Override
    public String getCorrectAnswer() {
        return null;
    }

    @Override
    public boolean inputAnswer(String answer) {
        return false;
    }

    @Override
    public boolean isEndOfDeck() {
        return false;
    }
}
