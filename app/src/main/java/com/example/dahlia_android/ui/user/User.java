package com.example.dahlia_android.ui.user;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.groups.Groups;
import com.example.dahlia_android.ui.home.Feed;
import com.example.dahlia_android.ui.messages.Messages;

public class User {

    private UserProfile userProfile;
    private String username;
    private String displayname;

    private int followingCount;
    private int followerCount;
    private FriendsList friends;
    private Feed feed;
    private Groups groups;
    private Messages messages;
    //private AuPairNearBy nearby; // if notEnabled then isEmpty

    public User(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public User() {
        this.userProfile = new UserProfile("testName1", "testUser1");
        this.followingCount = 0;
        this.followerCount = 0;
        this.friends = new FriendsList();
        this.feed = new Feed();
        this.groups = new Groups();
        this.messages = new Messages();
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }
}
