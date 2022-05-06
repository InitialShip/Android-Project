package com.example.englishdictionary;

public class Languages {
    public final static String[] lans = {"en", "ar", "zh", "fa", "fr", "ka", "de", "el", "gu", "ha", "hi", "ig", "id",
            "xh", "zu", "it", "lv", "ms", "mr", "nso", "pt", "qu", "ro", "ru", "tn", "es", "sw"
            ,"tg", "ta", "tt", "te", "tpi", "tk", "ur", "yo"};
    public final static String[] displays = {"English", "Arabic", "Chinese", "Farsi", "French", "Georgian", "German"
            , "Greek", "Gujarati", "Hausa", "Hindi", "Igbo", "Indonesia", "isiXhosa", "isiZulu"
            , "Italian", "Latvian", "Malay", "Marathi", "Northern Sotho", "Portuguese", "Quechua"
            , "Romanian", "Russian", "Setswana", "Spanish", "Swahili", "Tajik", "Tamil", "Tatar"
            , "Telugu", "Tok Pisin", "Turkmen", "Urdu", "Yoruba"};

    public static String[] getLans() {
        return lans;
    }

    public static String[] getDisplays() {
        return displays;
    }

    public static String getDisplayByShort(String lan) {
        for(int i = 0; i < lans.length; i++) {
            if(lan.equals(lans[i]))
                return displays[i];
        }
        return null;
    }
}
