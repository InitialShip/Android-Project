package com.example.englishdictionary.practise;

import java.io.Serializable;

public class Card implements Serializable {
    private String frontFace; // word
    private String backFace; // word's meaning or something
    private String hint;
    private String soundURL;
    private WordType type;
    private int correct;
    private int wrong;
    private int total;

    public Card() {
    }

    public Card(String front,String back,WordType type){
        this.frontFace = front;
        this.backFace = back;
        this.type = type;
    }

    public void increaseCorrect(){
        this.correct++;
    }

    public void increaseWrong(){
        this.wrong++;
    }

    public void increaseTotal(){
        this.total++;
    }

    public double getRate(){
        if(total == 0)
            return -1.0;
        return (correct - wrong)*1.0 / total; // LMAOOOOOOOOOOO
    }

    public int compare(Card otherCard) {
        return Double.compare(this.getRate(), otherCard.getRate());
    }

    public String getFrontFace() {
        return frontFace;
    }

    public void setFrontFace(String frontFace) {
        this.frontFace = frontFace;
    }

    public String getBackFace() {
        return backFace;
    }

    public void setBackFace(String backFace) {
        this.backFace = backFace;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getSoundURL() {
        return soundURL;
    }

    public void setSoundURL(String soundURL) {
        this.soundURL = soundURL;
    }

    public WordType getType() {
        return type;
    }

    public void setType(WordType type) {
        this.type = type;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }



}
