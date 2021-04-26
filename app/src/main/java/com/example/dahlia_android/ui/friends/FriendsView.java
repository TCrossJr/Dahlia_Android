package com.example.dahlia_android.ui.friends;

import com.example.dahlia_android.ui.user.User;

import java.util.ArrayList;

/**
 * Class exposing authenticated friendsList details to the UI.
 */
class FriendsView {
    private FriendsList _friendsList;
    //... other data fields that may be accessible to the UI

    FriendsView(FriendsList friendsList) {
        this._friendsList = friendsList;
    }

    FriendsView(ArrayList<User> friendsList) {
        this._friendsList = new FriendsList(friendsList);
    }

    FriendsList getFriendsList() {
        return _friendsList;
    }
}