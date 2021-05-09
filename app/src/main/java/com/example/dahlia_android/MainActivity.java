package com.example.dahlia_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dahlia_android.ui.about.AboutActivity;
import com.example.dahlia_android.ui.groups.Groups;
import com.example.dahlia_android.ui.help.HelpActivity;
import com.example.dahlia_android.ui.home.Feed;
import com.example.dahlia_android.ui.login.LoginActivity;
import com.example.dahlia_android.ui.messages.ConversationActivity;
import com.example.dahlia_android.ui.nearby.AuPairNearByMapActivity;
import com.example.dahlia_android.ui.user.User;
import com.example.dahlia_android.ui.user.UserProfileActivity;
import com.example.dahlia_android.ui.user.UserProfileCombinedList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    // TODO: MOVE to data classes(ViewModels/Repositories)
    public static UserProfileCombinedList _user_profile = new UserProfileCombinedList(); // TODO: MOVE/RMV
    public static Feed _homeFeed = new Feed(); // TODO: MOVE/RMV
    public static Groups _groupsList = new Groups(); // TODO: MOVE/RMV

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
        ApplicationUser.setCurrentUser(user);
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
}