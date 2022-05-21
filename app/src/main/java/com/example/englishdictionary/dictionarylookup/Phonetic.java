package com.example.englishdictionary.dictionarylookup;

public class Phonetic {
    public String word;
    public String wordSpelling;
    public String audioFile;

    public Phonetic(String word, String wordSpelling, String audioFile) {
        this.word = word;
        this.wordSpelling = wordSpelling;
        this.audioFile = audioFile;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordSpelling() {
        return wordSpelling;
    }

    public void setWordSpelling(String wordSpelling) {
        this.wordSpelling = wordSpelling;
    }

    public String getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(String audioFile) {
        this.audioFile = audioFile;
    }
}
