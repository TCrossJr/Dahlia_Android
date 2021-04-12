package com.example.dahlia_android.ui.user;

import com.example.dahlia_android.ui.user.UserProfile;

public class User {

    private UserProfile userProfile;
    private String user_displayName; // TODO: RMV Being used right now in FriendsListAdapter
    private String user_userName;

    public User(UserProfile userProfile, String user_displayName, String user_userName) {
        this.userProfile = userProfile;
        this.user_displayName = user_displayName;
        this.user_userName = user_userName;
    }

    public String getUserName() {
        return user_userName;
    }

    public String getDisplayName() {
        return user_displayName;
    }
}
