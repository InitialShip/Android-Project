package com.example.englishdictionary.practise;

import java.util.Date;
import java.util.List;

public class Deck {
    private String deckName;
    private List<Card> cards;
    private Date latestPractise;

    public Deck(){
    }

    public void sortDeck(){
        cards.sort(Card::compare);
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

    public Date getLatestPractise() {
        return latestPractise;
    }

    public void setLatestPractise(Date latestPractise) {
        this.latestPractise = latestPractise;
    }


}
