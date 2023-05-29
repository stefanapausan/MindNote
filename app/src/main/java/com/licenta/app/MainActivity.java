package com.licenta.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.licenta.app.Fragment.AffirmationFragment;
import com.licenta.app.Fragment.DrawingFragment;
import com.licenta.app.Fragment.HomeFragment;
import com.licenta.app.Fragment.JournalFragment;
import com.licenta.app.Fragment.MindfulnessFragment;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    public static final String DarkModePREFERENCES = "DarkModePreferences" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new HomeFragment());
        navigation.setSelectedItemId(R.id.bottom_nav_home);

        SharedPreferences sharedpreferences = getSharedPreferences(DarkModePREFERENCES, Context.MODE_PRIVATE);
        int DARK_MODE = sharedpreferences.getInt("DARK_MODE", 1);
        if (DARK_MODE == 1) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        else if (DARK_MODE == 2) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.bottom_nav_home:
                loadFragment(new HomeFragment());
                return true;
            case R.id.bottom_nav_journal:
                loadFragment(new JournalFragment());
                return true;
            case R.id.bottom_nav_drawing:
                loadFragment(new DrawingFragment());
                return true;
            case R.id.bottom_nav_affirmation:
                loadFragment(new AffirmationFragment());
                return true;
            case R.id.bottom_nav_mindfulness:
                loadFragment(new MindfulnessFragment());
                return true;
        }
        return false;
    };

    public void loadJournalFragment() {
        loadFragment(new JournalFragment());
        navigation.setSelectedItemId(R.id.bottom_nav_journal);
    }
    public void loadDrawingFragment() {
        loadFragment(new DrawingFragment());
        navigation.setSelectedItemId(R.id.bottom_nav_drawing);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment).addToBackStack(null);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return true;
            case R.id.action_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("YES", (dialogInterface, i) -> finish())
                .setNegativeButton("NO", null)
                .show();
    }
}