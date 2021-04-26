package com.example.dahlia_android;

import android.app.Application;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.user.User;

// TODO: Move to ViewModel/DataSource/DataRepository???
public class ApplicationUser extends Application {
    static User current_user;
    static FriendsList friends_list;

    public static FriendsList getFriendsList() {
        return friends_list;
    }

    public static void setFriendsList(FriendsList friends_list) {
        ApplicationUser.friends_list = friends_list;
    }

    public static User getCurrentUser() {
        return current_user;
    }

    public static void setCurrentUser(User current_user) {
        ApplicationUser.current_user = current_user;
    }
}
