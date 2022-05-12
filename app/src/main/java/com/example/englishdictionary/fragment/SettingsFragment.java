package com.example.englishdictionary.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.example.englishdictionary.R;
import com.example.englishdictionary.settings.datalocal.DataLocalManager;

public class SettingsFragment extends PreferenceFragmentCompat {
    public static final String LOCALE_KEY = "locale";
    public static final String THEME_KEY = "theme";

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        addPreferencesFromResource(R.xml.setting_preferences);
        
        ListPreference change_lang = (ListPreference) findPreference(LOCALE_KEY);
        assert change_lang != null;
        if(DataLocalManager.getPrefs(LOCALE_KEY).equals("vi"))
            change_lang.setSummary("Vietnamese");
        else if(DataLocalManager.getPrefs(LOCALE_KEY).equals("en"))
            change_lang.setSummary("English");
        else if(DataLocalManager.getPrefs(LOCALE_KEY).equals("ja"))
            change_lang.setSummary("Japanese");
        else
            change_lang.setSummary("Vietnamese");
        change_lang.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                if(newValue.toString().equals("Vietnamese")) {
                    DataLocalManager.setPrefs(LOCALE_KEY, "vi");
                } else if (newValue.toString().equals("English")) {
                    DataLocalManager.setPrefs(LOCALE_KEY, "en");
                } else if(newValue.toString().equals("Japanese"))
                    DataLocalManager.setPrefs(LOCALE_KEY, "ja");
                change_lang.setSummary(newValue.toString());
                return true;
            }
        });
    }
}
