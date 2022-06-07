package com.example.englishdictionary.practise;

public class Card {
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

    public double getRate(){
        return (correct - wrong)*1.0 / total; // LMAOOOOOOOOOOO
    }

    public int compare(Card otherCard) {
        if(this.total < otherCard.total)
            return -1;
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
