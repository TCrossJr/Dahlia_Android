package com.example.dahlia_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;

import com.example.dahlia_android.ui.friends.FriendsFragment;
import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.groups.GroupsFragment;
import com.example.dahlia_android.ui.home.HomeFeed;
import com.example.dahlia_android.ui.home.HomeFeedActivity;
import com.example.dahlia_android.ui.home.HomeFragment;
import com.example.dahlia_android.ui.home.Post;
import com.example.dahlia_android.ui.login.LoginActivity;
import com.example.dahlia_android.ui.messages.MessagesFragment;
import com.example.dahlia_android.ui.user.User;
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

    public static FriendsList _friendsList;
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
        BottomNavigationView btm = findViewById(R.id.navigation); // TODO: Fix navigation DrawerLayout and BottomNavigation not interacting well...

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // TODO: CHANGE to setOpenableLayout(Openable)
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_friends, R.id.nav_groups, R.id.nav_messages, R.id.nav_aupair_nearby, R.id.nav_login_test)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(btm, navController);
        testFriends();
    }

    private void testFriends() {
        FriendsList friends = new FriendsList();

        friends.add( new User(null, "TestDisplayName1", "TestUserName1"));
        friends.add( new User(null, "TestDisplayName2", "TestUserName2"));
        friends.add( new User(null, "TestDisplayName3", "TestUserName3"));
        friends.add( new User(null, "TestDisplayName4", "TestUserName4"));
        friends.add( new User(null, "TestDisplayName5", "TestUserName5"));
        friends.add( new User(null, "TestDisplayName6", "TestUserName6"));
        friends.add( new User(null, "TestDisplayName7", "TestUserName7"));
        friends.add( new User(null, "TestDisplayName8", "TestUserName8"));
        friends.add( new User(null, "TestDisplayName9", "TestUserName9"));
        friends.add( new User(null, "TestDisplayName10", "TestUserName10"));
        friends.add( new User(null, "TestDisplayName11", "TestUserName11"));
        friends.add( new User(null, "TestDisplayName12", "TestUserName12"));
        friends.add( new User(null, "TestDisplayName13", "TestUserName13"));
        friends.add( new User(null, "TestDisplayName14", "TestUserName14"));
        friends.add( new User(null, "TestDisplayName15", "TestUserName15"));
        friends.add( new User(null, "TestDisplayName16", "TestUserName16"));
        friends.add( new User(null, "TestDisplayName17", "TestUserName17"));
        friends.add( new User(null, "TestDisplayName18", "TestUserName18"));
        friends.add( new User(null, "TestDisplayName19", "TestUserName19"));
        friends.add( new User(null, "TestDisplayName20", "TestUserName20"));




        _friendsList = friends;
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

    public void goLogin(MenuItem item) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goLogout(MenuItem item) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}