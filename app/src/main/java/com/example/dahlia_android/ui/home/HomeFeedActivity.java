package com.example.dahlia_android.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dahlia_android.R;

public class HomeFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_homefeed);

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.nav_host_fragment, HomeFeedFragment.newInstance())
                    .commit();
        }
    }
}