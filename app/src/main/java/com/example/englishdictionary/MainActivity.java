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

import com.example.englishdictionary.fragment.PractiseFragment;
import com.example.englishdictionary.fragment.SearchFragment;
import com.example.englishdictionary.fragment.TransFragment;
import com.example.englishdictionary.settings.LanguageConfig;
import com.example.englishdictionary.settings.datalocal.DataLocalManager;
import com.example.englishdictionary.settings.datalocal.MySharePreferences;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_SEARCH = 0;
    private static final int FRAGMENT_TRANS = 1;
    private static final int FRAGMENT_PRACTISE = 2;

    private int currentFragment = FRAGMENT_SEARCH;
    ActionBarDrawerToggle toggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;
    public static final String LOCALE_KEY = "locale";
    public static final String THEME_KEY = "theme";
    public static final String FRAGMENT_KEY = "fragment";

    public static FragmentTransaction ft;

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
        ft = getSupportFragmentManager().beginTransaction();
        changeTheme();
        MySharePreferences mySharePreferences = new MySharePreferences(this);

        if (mySharePreferences.getStringValue("last_source_lang").isEmpty())
            MyApplication.setCurrent_source("en");
        else
            MyApplication.setCurrent_source(mySharePreferences.getStringValue("last_source_lang"));

        if(mySharePreferences.getStringValue("last_target_lang").isEmpty())
            MyApplication.setCurrent_target("es");
        else
            MyApplication.setCurrent_target(mySharePreferences.getStringValue("last_target_lang"));

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

            String chosen_lang = intent.getStringExtra("lang");
            if (chosen_lang != null) {
                if (check_btn == 1) {
                    if (chosen_lang.equals(MyApplication.getCurrent_source())) {
                        String temp;
                        temp = MyApplication.getCurrent_target();
                        MyApplication.setCurrent_target(MyApplication.getCurrent_source());
                        MyApplication.setCurrent_source(temp);
                    }
                    else
                        MyApplication.setCurrent_target(chosen_lang);
                }
                else {
                    if (chosen_lang.equals(MyApplication.getCurrent_target())) {
                        String temp;
                        temp = MyApplication.getCurrent_source();
                        MyApplication.setCurrent_source(MyApplication.getCurrent_target());
                        MyApplication.setCurrent_target(temp);
                    }
                    else
                        MyApplication.setCurrent_source(chosen_lang);
                }

            }
            replaceFragment(fragment);
            navView.getMenu().findItem(R.id.nav_trans).setChecked(true);
        }
        if(check_frag == 0){
            currentFragment = FRAGMENT_SEARCH;
            replaceFragment(new SearchFragment());
            navView.getMenu().findItem(R.id.nav_search).setChecked(true);
        }
        if(check_frag == 2){
            currentFragment = FRAGMENT_PRACTISE;
            replaceFragment(new PractiseFragment());
            navView.getMenu().findItem(R.id.nav_practise).setChecked(true);

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
        } else
            if (id == R.id.nav_trans) {
            replaceFragment(new TransFragment());
            getSupportActionBar().setTitle(R.string.nav_trans);
            currentFragment = FRAGMENT_TRANS;
            } else if(id == R.id.nav_practise){
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, new PractiseFragment(),"Practise Fragment");
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.nav_practise);
                currentFragment = FRAGMENT_PRACTISE;
            } else if (id == R.id.nav_setting) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);}

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }
    public static FragmentTransaction getFragmentTransaction(){
        return MainActivity.ft;
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (currentFragment == 1)
            navView.getMenu().findItem(R.id.nav_trans).setChecked(true);
        else
            navView.getMenu().findItem(R.id.nav_search).setChecked(true);
    }
}