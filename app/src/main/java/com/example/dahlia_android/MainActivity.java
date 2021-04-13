package com.example.dahlia_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.Menu;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.groups.Group;
import com.example.dahlia_android.ui.groups.Groups;
import com.example.dahlia_android.ui.home.Feed;
import com.example.dahlia_android.ui.home.Post;
import com.example.dahlia_android.ui.login.LoginActivity;
import com.example.dahlia_android.ui.messages.Message;
import com.example.dahlia_android.ui.messages.Messages;
import com.example.dahlia_android.ui.user.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    // TODO: MOVE to data classes(ViewModels/Repositories)
    public static Feed _homefeed;
    public static FriendsList _friendsList;
    public static Messages _messageList;
    public static Groups _groupsList;

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
        testRecyclerViews();
    }

    private void testRecyclerViews() {
        // Tmp Testing Posts Feed
        Feed feed = new Feed();
        feed.add(new Post("This is the First Post", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)", "45s"));
        feed.add(new Post("This is the Last Post", "45s"));
        _homefeed = feed;

        // Tmp Testing Groups
        Groups groups = new Groups();
        groups.add(new Group("TestGroupName1", "This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)"));
        groups.add(new Group("TestGroupName2", "This is a test message that is Short length.(ch=51)"));
        groups.add(new Group("TestGroupName3", "TEST.(ch=12)"));
        groups.add(new Group("TestGroupName4", "This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)"));
        groups.add(new Group("TestGroupName5", "This is a test message that is Short length.(ch=51)"));
        groups.add(new Group("TestGroupName6", "TEST.(ch=12)"));
        groups.add(new Group("TestGroupName7", "This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)"));
        groups.add(new Group("TestGroupName8", "This is a test message that is Short length.(ch=51)"));
        groups.add(new Group("TestGroupName9", "TEST.(ch=12)"));
        groups.add(new Group("TestGroupName10","This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)"));
        groups.add(new Group("TestGroupName11","This is a test message that is Short length.(ch=51)"));
        groups.add(new Group("TestGroupName12","TEST.(ch=12)"));
        groups.add(new Group("TestGroupName13","This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)"));
        groups.add(new Group("TestGroupName14","This is a test message that is Short length.(ch=51)"));
        groups.add(new Group("TestGroupName15","TEST.(ch=12)"));
        groups.add(new Group("TestGroupName16","This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)"));
        groups.add(new Group("TestGroupName17","This is a test message that is Short length.(ch=51)"));
        groups.add(new Group("TestGroupName18","TEST.(ch=12)"));
        groups.add(new Group("TestGroupName19","This is a test message that is Long length. The message needs to be a certain length to check if it displays correctly. The message needs to be slightly longer(ch=167)"));
        groups.add(new Group("TestGroupName20","This is a test message that is Short length.(ch=51)"));
        _groupsList = groups;

        // Tmp Testing Messages
        Messages messages = new Messages();
        messages.add(new Message(null,"","TestDisplayName1", "4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName2", "4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName3", "4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName4", "4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName5", "4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName6", "4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName7", "4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName8", "4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName9", "4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName10","4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName11","4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName12","4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName13","4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName14","4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName15","4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName16","4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName17","4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName18","4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName19","4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        messages.add(new Message(null,"","TestDisplayName20","4/11/21", "Hey what's up, this is a test message to see how the Messages look.", null));
        _messageList = messages;

        // Tmp Test Friends
        FriendsList friends = new FriendsList();
        friends.add( new User(null, "@TestDisplayName1",  "TestUserName1"));
        friends.add( new User(null, "@TestDisplayName2",  "TestUserName2"));
        friends.add( new User(null, "@TestDisplayName3",  "TestUserName3"));
        friends.add( new User(null, "@TestDisplayName4",  "TestUserName4"));
        friends.add( new User(null, "@TestDisplayName5",  "TestUserName5"));
        friends.add( new User(null, "@TestDisplayName6",  "TestUserName6"));
        friends.add( new User(null, "@TestDisplayName7",  "TestUserName7"));
        friends.add( new User(null, "@TestDisplayName8",  "TestUserName8"));
        friends.add( new User(null, "@TestDisplayName9",  "TestUserName9"));
        friends.add( new User(null, "@TestDisplayName10", "TestUserName10"));
        friends.add( new User(null, "@TestDisplayName11", "TestUserName11"));
        friends.add( new User(null, "@TestDisplayName12", "TestUserName12"));
        friends.add( new User(null, "@TestDisplayName13", "TestUserName13"));
        friends.add( new User(null, "@TestDisplayName14", "TestUserName14"));
        friends.add( new User(null, "@TestDisplayName15", "TestUserName15"));
        friends.add( new User(null, "@TestDisplayName16", "TestUserName16"));
        friends.add( new User(null, "@TestDisplayName17", "TestUserName17"));
        friends.add( new User(null, "@TestDisplayName18", "TestUserName18"));
        friends.add( new User(null, "@TestDisplayName19", "TestUserName19"));
        friends.add( new User(null, "@TestDisplayName20", "TestUserName20"));
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