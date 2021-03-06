package com.example.dahlia_android;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dahlia_android.ui.about.AboutActivity;
import com.example.dahlia_android.ui.help.HelpActivity;
import com.example.dahlia_android.ui.login.LoginActivity;
import com.example.dahlia_android.ui.messages.ConversationActivity;
import com.example.dahlia_android.ui.nearby.AuPairNearByMapActivity;
import com.example.dahlia_android.ui.user.User;
import com.example.dahlia_android.ui.user.UserProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: RMV when switch to Async or Https
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        BottomNavigationView btm = findViewById(R.id.navigation);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // TODO: CHANGE to setOpenableLayout(Openable) and remove login_test
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_friends, R.id.nav_groups, R.id.nav_messages, R.id.nav_aupair_nearby, R.id.nav_login, R.id.nav_signup)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(btm, navController);
        loadUser();
//        testRecyclerViews();
    }

    // TODO: Move to ViewModel/DataSource/DataRepository
    private void loadUser() {
        SharedPreferences preferences = getSharedPreferences("userObject", MODE_PRIVATE);
        String userJsonString = preferences.getString("user", "" );
        User user = new GsonBuilder().create().fromJson(userJsonString, User.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(
                new ComponentName(this,SearchableActivity.class)
        ));
        searchView.setIconifiedByDefault(true);

        return true;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, "Searching by: " + query, Toast.LENGTH_SHORT).show();
        } else if(Intent.ACTION_VIEW.equals(intent.getAction())) {
            String uri = intent.getDataString();
            Toast.makeText(this,"Suggestion: "+uri, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // TODO: WIP
    public void goToProfile(View view) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void goToProfile(View view, User usr) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("ThisUser", (Serializable) usr); // TODO: FIX/CHANGE/RMV doesn't pass User object
        startActivity(intent);
    }

    public void goToConversation(View view) {
        Intent intent = new Intent(this, ConversationActivity.class);
        startActivity(intent);
    }

    public void goLogin(MenuItem item) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goAbout(MenuItem item) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void goFindNearby(View view) {
        Intent intent = new Intent(this, AuPairNearByMapActivity.class);
        startActivity(intent);
    }

    public void goHelp(MenuItem item) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void goLogout(View view) {
        //TODO: Implement
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}