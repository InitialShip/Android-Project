package com.example.englishdictionary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.englishdictionary.fragment.SearchFragment;
import com.example.englishdictionary.fragment.TransFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private static final int FRAGMENT_SEARCH = 0;
    private static final int FRAGMENT_TRANS = 1;

    private int currentFragment = FRAGMENT_SEARCH;
    ActionBarDrawerToggle toggle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


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

        NavigationView navView =  findViewById(R.id.navigation_view);
        navView.setNavigationItemSelectedListener(this);
        replaceFragment(new SearchFragment());
        navView.getMenu().findItem(R.id.nav_search).setChecked(true);
    }

    private String dictionaryEntries() {
        final String language = "en-gb";
        final String word = "Ace";
        final String fields = "pronunciations";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" +
                language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

//    @Override
//    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        toggle.syncState();
//    }

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
                currentFragment = FRAGMENT_SEARCH;
            }
        } else if (id == R.id.nav_trans) {
            replaceFragment(new TransFragment());
            currentFragment = FRAGMENT_TRANS;
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