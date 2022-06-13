package com.example.englishdictionary.practise;

import com.example.englishdictionary.practise.file.FileHandler;
import com.example.englishdictionary.practise.file.Meta;
import com.example.englishdictionary.practise.mode.Normal;
import com.example.englishdictionary.practise.mode.PractiseMode;
import com.example.englishdictionary.practise.mode.Reverb;

import java.util.ArrayList;
import java.util.List;

public class PractiseManager {
    private Meta meta;
    private List<Deck> decks;

    private static PractiseMode selectedMode;
    private final Normal normalM; // default mode
    private final Reverb reverbM;

    public PractiseManager(){
        this.decks = new ArrayList<>();
        this.normalM = new Normal();
        this.reverbM = new Reverb();

        selectedMode = normalM;
    }
    public static PractiseMode getMode(){
        return selectedMode;
    }
    public void sortDecks(){
        decks.sort(Deck::compare);
    }

    public Deck getSelectedDeck() {
        return selectedMode.getSelectedDeck();
    }

    public void setSelectedDeck(Deck newDeck) {
        selectedMode.setSelectedDeckDeck(newDeck);
    }

    public void setMeta(Meta meta){
        this.meta = meta;
    }
    public Meta getMeta(){
        return meta;
    }
    public void setDecks(List<Deck> decks){
        this.decks = decks;
    }
    public List<Deck> getDecks(){
        return decks;
    }
    public void changeMode(PractiseMode newMode){
        selectedMode = newMode;
    }
    public void changeToNormal(){
        selectedMode = normalM;
    }
    public void changeToReverb(){
        selectedMode = reverbM;
    }
}
