package com.example.englishdictionary.practise;

import com.example.englishdictionary.practise.mode.Normal;
import com.example.englishdictionary.practise.mode.PractiseMode;
import com.example.englishdictionary.practise.mode.Reverb;

public class PractiseManager {
    private static PractiseMode selectedMode;
    private static Normal normalM; // default mode
    private static Reverb reverbM;

    public PractiseManager(){
        normalM = new Normal();
        reverbM = new Reverb();

        selectedMode = normalM;
    }

    public void changeMode(PractiseMode newMode){
        selectedMode = newMode;
    }
}
