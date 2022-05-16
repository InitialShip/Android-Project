package com.example.englishdictionary.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

import com.example.englishdictionary.MainActivity;
import com.example.englishdictionary.R;
import com.example.englishdictionary.settings.datalocal.DataLocalManager;

import java.util.Objects;

public class SettingsFragment extends PreferenceFragmentCompat {
    public static final String LOCALE_KEY = "locale";
    public static final String THEME_KEY = "theme";

    SwitchPreference change_theme;
    ListPreference change_lang;
    Preference contact_us;

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        addPreferencesFromResource(R.xml.setting_preferences);

        change_theme = (SwitchPreference) findPreference(THEME_KEY);
        assert change_theme != null;
        change_theme.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                boolean isChecked = (boolean) newValue;
                if(isChecked) {
                    enableChangeDarkTheme();
                    reStart();
                } else if(!isChecked){
                    disableChangeDarkTheme();
                    reStart();
                }
                return true;
            }
        });

        change_lang = (ListPreference) findPreference(LOCALE_KEY);
        assert change_lang != null;
        switch (DataLocalManager.getStringPrefs(LOCALE_KEY)) {
            case "vi":
                change_lang.setSummary("Vietnamese");
                break;
            case "en":
                change_lang.setSummary("English");
                break;
            case "ja":
                change_lang.setSummary("Japanese");
                break;
        }

        change_lang.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                changeLanguage(newValue);
                reStart();
                return true;
            }
        });
    }

    private void changeLanguage(Object value) {
        DataLocalManager.setStringPrefs(LOCALE_KEY, value.toString());
    }

    private void enableChangeDarkTheme() {
        DataLocalManager.setBooleanPrefs(THEME_KEY, true);
    }

    private void disableChangeDarkTheme() {
        DataLocalManager.setBooleanPrefs(THEME_KEY, false);
    }

    private void reStart() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }, 1000);
    }
}
