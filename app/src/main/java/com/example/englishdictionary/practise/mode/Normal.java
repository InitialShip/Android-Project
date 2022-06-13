package com.example.englishdictionary.practise.mode;

import com.example.englishdictionary.practise.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Normal extends PractiseMode{
    private static int cardIndex;
    private static int cardAmount;
    private static List<Card> cardList;
    public Normal(){
        this.name = "Normal Mode";
        this.description = "Normal Mode"; //TODO change
    }

    @Override
    public void enter() {
        cardIndex = 0;
        cardAmount = this.selectedDeck.getAmount();
        cardAmount = 4;
        cardList = this.selectedDeck.getCards();
    }

    @Override
    public void exit() {
        cardList = null;
    }

    @Override
    public String getQuestion() {
        return cardList.get(cardIndex).getFrontFace();
    }

    @Override
    public List<String> getAnswers() {
        List<String> answers = new ArrayList<>();
        answers.add(getCorrectAnswer());
        while (answers.size() < 4){
            Card card = cardList.get(rand.nextInt(cardAmount));
            if(card == cardList.get(cardIndex)) // TODO fix
                continue;
            answers.add(card.getBackFace());
        }
        Collections.shuffle(answers);
        return answers;
    }

    @Override
    public String getCorrectAnswer() {
        return cardList.get(cardIndex).getBackFace();
    }

    @Override
    public boolean inputAnswer(String answer) {
        cardList.get(cardIndex).increaseTotal();

        if(cardList.get(cardIndex).getBackFace().equals(answer)){
            cardList.get(cardIndex).increaseCorrect();
            cardIndex++;
            return true;
        }
        cardList.get(cardIndex).increaseWrong();
        cardIndex++;
        return false;
    }

    @Override
    public boolean isEndOfDeck() {
        return cardIndex >= cardAmount;
    }
}
