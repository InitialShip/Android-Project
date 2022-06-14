package com.example.englishdictionary.practise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Deck implements Serializable {
    private String deckName;
    private List<Card> cards;
    private String latestPractise;
    private final String prefName;

    private static final int MAX_AMOUNT = 50;
    private static final int MAX_LENGTH = 30;

    public Deck(){
        prefName = UUID.randomUUID().toString();
        cards = new ArrayList<>();
        latestPractise = "None";
    }
    public Deck(String deckName){
        if(deckName.length() > MAX_LENGTH)
            this.deckName = deckName.substring(0,20);
        this.deckName = deckName;
        prefName = deckName.replaceAll("\\s+","").toLowerCase() + UUID.randomUUID().toString();
        cards = new ArrayList<>();
    }
    public String getPrefName(){
        return prefName;
    }

    public int getAmount(){
        return cards.size();
    }

    public void addCards(List<Card> cards){
        this.cards = cards;
    }
    public void addCard(Card newCard){
        if(cards.size() < MAX_AMOUNT)
            cards.add(newCard);
    }

    public void removeCard(Card card){
        cards.remove(card);
    }

    public void sortDeck(){
        cards.sort((Card::compare));
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public String getLatestPractise() {
        return latestPractise;
    }

    public void setLatestPractise(String latestPractise) {
        this.latestPractise = latestPractise;
    }

    public int compare(Deck otherDeck) {
        return this.getDeckName().compareTo(otherDeck.getDeckName());
    }

}
