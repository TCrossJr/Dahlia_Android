package com.example.dahlia_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;

import com.example.dahlia_android.ui.friends.FriendsFragment;
import com.example.dahlia_android.ui.groups.GroupsFragment;
import com.example.dahlia_android.ui.home.HomeFragment;
import com.example.dahlia_android.ui.login.LoginActivity;
import com.example.dahlia_android.ui.messages.MessagesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static android.content.Intent.EXTRA_EMAIL;
import static android.content.Intent.EXTRA_USER;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        BottomNavigationView btm = findViewById(R.id.navigation);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_friends, R.id.nav_groups, R.id.nav_messages, R.id.nav_aupair_nearby)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(btm, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void goHome(MenuItem item) {
        item.setChecked(true);
        FragmentManager managerHome = getSupportFragmentManager();
        if(managerHome != null) {
            FragmentTransaction transaction = managerHome.beginTransaction();
            if( transaction != null ) {
                transaction.replace(R.id.drawer_layout,new HomeFragment());
                transaction.commit();
            }
        }
    }

    public void goFriends(MenuItem item) {
        item.setChecked(true);
        FragmentManager managerFriends = getSupportFragmentManager();
        if (managerFriends != null ) {
            FragmentTransaction transaction = managerFriends.beginTransaction();
            if( transaction != null ) {
                transaction.replace(R.id.drawer_layout,new FriendsFragment());
                transaction.commit();
            }
        }
    }

    public void goGroups(MenuItem item) {
        item.setChecked(true);
        FragmentManager managerGroups = getSupportFragmentManager();
        if (managerGroups != null ) {
            FragmentTransaction transaction = managerGroups.beginTransaction();
            if( transaction != null ) {
                transaction.replace(R.id.drawer_layout,new GroupsFragment());
                transaction.commit();
            }
        }
    }

    public void goMessages(MenuItem item) {
        item.setChecked(true);
        FragmentManager managerMessages = getSupportFragmentManager();
        if (managerMessages != null ) {
            FragmentTransaction transaction = managerMessages.beginTransaction();
            if( transaction != null ) {
                transaction.replace(R.id.drawer_layout,new MessagesFragment());
                transaction.commit();
            }
        }
    }

    public void goAuPairNearby(MenuItem item) {
    }

    public void goRSSFeed(MenuItem item) {
    }

    public void goAbout(MenuItem item) {
    }

    public void goSettings(MenuItem item) {
    }

    public void goLogin(MenuItem item) {
        item.setChecked(true);
        Intent intent = new Intent(this, LoginActivity.class);
        /* TODO: REMOVE/MOVE?
        EditText userEmail = findViewById(R.id.username);
        EditText userPW = findViewById(R.id.password);
        String email = userEmail.getText().toString();
        String pw = userEmail.getText().toString();
        intent.putExtra("uem", (CharSequence) userEmail);
        intent.putExtra("upw", (CharSequence) userEmail);*/
        startActivity(intent);
    }
}