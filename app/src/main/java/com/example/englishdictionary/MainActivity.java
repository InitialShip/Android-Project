package com.example.englishdictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.englishdictionary.fragment.SearchFragment;
import com.example.englishdictionary.fragment.TransFragment;
import com.example.englishdictionary.settings.LanguageConfig;
import com.example.englishdictionary.settings.datalocal.DataLocalManager;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_SEARCH = 0;
    private static final int FRAGMENT_TRANS = 1;

    private int currentFragment = FRAGMENT_SEARCH;
    ActionBarDrawerToggle toggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;
    public static final String LOCALE_KEY = "locale";
    public static final String THEME_KEY = "theme";
    public static final String FRAGMENT_KEY = "fragment";

    @Override
    protected void attachBaseContext(Context newBase) {
        String languageCode = DataLocalManager.getStringPrefs(LOCALE_KEY);
        Context context = LanguageConfig.changeLanguage(newBase, languageCode);
        super.attachBaseContext(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeTheme();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.nav_search);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        mDrawerLayout.addDrawerListener(toggle);
        try {

            toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            toggle.syncState();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }

        navView = findViewById(R.id.navigation_view);
        navView.setNavigationItemSelectedListener(this);
        checkFragment();
    }

    private void changeTheme() {
        if (DataLocalManager.getBooleanPrefs(THEME_KEY)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if(!DataLocalManager.getBooleanPrefs(THEME_KEY))
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

    void checkFragment() {
        Intent intent = getIntent();
        int check_frag = intent.getIntExtra("check_frag", 0);
        int check_btn = intent.getIntExtra("check_btn", 0);

        if (check_frag == 1) {
            TransFragment fragment = new TransFragment();
            currentFragment = FRAGMENT_TRANS;

            String choosed_lang = intent.getStringExtra("lang");
            if (choosed_lang != null) {
                if (check_btn == 1)
                    MyApplication.setCurrent_target(choosed_lang);
                else
                    MyApplication.setCurrent_source(choosed_lang);

            }
            replaceFragment(fragment);
            navView.getMenu().findItem(R.id.nav_trans).setChecked(true);
        } else {
            currentFragment = FRAGMENT_SEARCH;
            replaceFragment(new SearchFragment());
            navView.getMenu().findItem(R.id.nav_search).setChecked(true);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_search) {
            if(currentFragment != FRAGMENT_SEARCH) {
                replaceFragment(new SearchFragment());
                getSupportActionBar().setTitle(R.string.nav_search);
                currentFragment = FRAGMENT_SEARCH;
            }
        } else if (id == R.id.nav_trans) {
            replaceFragment(new TransFragment());
            getSupportActionBar().setTitle(R.string.nav_trans);
            currentFragment = FRAGMENT_TRANS;
        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }
}