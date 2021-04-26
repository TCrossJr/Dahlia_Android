package com.example.dahlia_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.groups.Group;
import com.example.dahlia_android.ui.groups.Groups;
import com.example.dahlia_android.ui.home.Feed;
import com.example.dahlia_android.ui.home.Post;
import com.example.dahlia_android.ui.login.LoginActivity;
import com.example.dahlia_android.ui.messages.Message;
import com.example.dahlia_android.ui.messages.Messages;
import com.example.dahlia_android.ui.user.User;
import com.example.dahlia_android.ui.user.UserProfile;
import com.example.dahlia_android.ui.user.UserProfileActivity;
import com.example.dahlia_android.ui.user.UserProfileCombinedList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.GsonBuilder;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    // TODO: MOVE to data classes(ViewModels/Repositories)
    public static UserProfileCombinedList _user_profile = new UserProfileCombinedList(); // TODO: MOVE/RMV
    public static Feed _homeFeed = new Feed(); // TODO: MOVE/RMV
    public static FriendsList _friendsList= new FriendsList(); // TODO: MOVE/RMV
    public static Messages _messageList = new Messages(); // TODO: MOVE/RMV
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
                R.id.nav_home, R.id.nav_friends, R.id.nav_groups, R.id.nav_messages, R.id.nav_aupair_nearby, R.id.nav_login_test)
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
        _homeFeed = feed;

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
//        friends.add( new User("@TestDisplayName1",  "TestUserName1"));
//        friends.add( new User("@TestDisplayName2",  "TestUserName2"));
//        friends.add( new User("@TestDisplayName3",  "TestUserName3"));
//        friends.add( new User("@TestDisplayName4",  "TestUserName4"));
//        friends.add( new User("@TestDisplayName5",  "TestUserName5"));
//        friends.add( new User("@TestDisplayName6",  "TestUserName6"));
//        friends.add( new User("@TestDisplayName7",  "TestUserName7"));
//        friends.add( new User("@TestDisplayName8",  "TestUserName8"));
//        friends.add( new User("@TestDisplayName9",  "TestUserName9"));
//        friends.add( new User("@TestDisplayName10", "TestUserName10"));
//        friends.add( new User("@TestDisplayName11", "TestUserName11"));
//        friends.add( new User("@TestDisplayName12", "TestUserName12"));
//        friends.add( new User("@TestDisplayName13", "TestUserName13"));
//        friends.add( new User("@TestDisplayName14", "TestUserName14"));
//        friends.add( new User("@TestDisplayName15", "TestUserName15"));
//        friends.add( new User("@TestDisplayName16", "TestUserName16"));
//        friends.add( new User("@TestDisplayName17", "TestUserName17"));
//        friends.add( new User("@TestDisplayName18", "TestUserName18"));
//        friends.add( new User("@TestDisplayName19", "TestUserName19"));
//        friends.add( new User("@TestDisplayName20", "TestUserName20"));
//        _friendsList = friends;

        // Tmp Test Friends2
        FriendsList friends2 = new FriendsList();
        friends2.add( new User(new UserProfile("username1","displayname1")));//, "@TestDisplayName1",  "TestUserName1"));
        friends2.add( new User(new UserProfile("username2","displayname2")));//, "@TestDisplayName2",  "TestUserName2"));
        friends2.add( new User(new UserProfile("username3","displayname3")));//, "@TestDisplayName3",  "TestUserName3"));
        friends2.add( new User(new UserProfile("username4","displayname4")));//, "@TestDisplayName4",  "TestUserName4"));
        friends2.add( new User(new UserProfile("username5","displayname5")));//, "@TestDisplayName5",  "TestUserName5"));
        friends2.add( new User(new UserProfile("username6","displayname6")));//, "@TestDisplayName6",  "TestUserName6"));
        friends2.add( new User(new UserProfile("username7","displayname7")));//, "@TestDisplayName7",  "TestUserName7"));
        friends2.add( new User(new UserProfile("username8","displayname8")));//, "@TestDisplayName8",  "TestUserName8"));
        friends2.add( new User(new UserProfile("username9","displayname9")));//, "@TestDisplayName9",  "TestUserName9"));
        friends2.add( new User(new UserProfile("username10","displayname10")));//, "@TestDisplayName10", "TestUserName10"));
        friends2.add( new User(new UserProfile("username11","displayname11")));//, "@TestDisplayName11", "TestUserName11"));
        friends2.add( new User(new UserProfile("username12","displayname12")));//, "@TestDisplayName12", "TestUserName12"));
        friends2.add( new User(new UserProfile("username13","displayname13")));//, "@TestDisplayName13", "TestUserName13"));
        friends2.add( new User(new UserProfile("username14","displayname14")));//, "@TestDisplayName14", "TestUserName14"));
        friends2.add( new User(new UserProfile("username15","displayname15")));//, "@TestDisplayName15", "TestUserName15"));
        friends2.add( new User(new UserProfile("username16","displayname16")));//, "@TestDisplayName16", "TestUserName16"));
        friends2.add( new User(new UserProfile("username17","displayname17")));//, "@TestDisplayName17", "TestUserName17"));
        friends2.add( new User(new UserProfile("username18","displayname18")));//, "@TestDisplayName18", "TestUserName18"));
        friends2.add( new User(new UserProfile("username19","displayname19")));//, "@TestDisplayName19", "TestUserName19"));
        friends2.add( new User(new UserProfile("username20","displayname20")));//, "@TestDisplayName20", "TestUserName20"));
        _friendsList = friends2;
        UserProfile profile = new UserProfile(  "username0", "displayname0",
                "dob...","town...","state...",  "zipcode...",
                "description...");
        UserProfileCombinedList feeds = new UserProfileCombinedList();
        feeds.add(profile);
        feeds.addAll(_homeFeed);
        feeds.addAll(_messageList);
        feeds.addAll(_groupsList);
        feeds.addAll(_friendsList);
        User usr = new User(profile);
        // TODO: MOVE/RMV
        _user_profile = feeds;

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

    public void goToProfile(View view, User usr) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("ThisUser", (Serializable) usr); // TODO: FIX/CHANGE/RMV doesn't pass User object
        startActivity(intent);
    }
}