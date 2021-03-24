package com.example.dahlia_android.ui.friends;

import java.util.ArrayList;
import java.util.List;

public class FriendsList extends ArrayList{

    private ArrayList<Friend> friendsList;

    public FriendsList() {
        friendsList = new ArrayList<>();
    }

    public List<Friend> getFriendsList() {
        return friendsList;
    }

    public Friend getFriend(int position) {
        return (Friend) friendsList.get(position);
    }

    public void addFriend( Friend friend ) {
        friendsList.add(friend);
    }
}
