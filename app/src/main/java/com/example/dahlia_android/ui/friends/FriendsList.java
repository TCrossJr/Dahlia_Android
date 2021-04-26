package com.example.dahlia_android.ui.friends;

import com.example.dahlia_android.ui.user.User;

import java.util.ArrayList;

public class FriendsList extends ArrayList{

    private ArrayList<User> friendsList;

    public FriendsList() {
        friendsList = new ArrayList<>();
    }

    public FriendsList(FriendsList friends) {
        friendsList = friends;
    }

    public FriendsList(ArrayList<User> friends) {
        friendsList = convertArrayList(friends);
    }

    private FriendsList convertArrayList(ArrayList<User> friendsList) {
        FriendsList friends = new FriendsList();
        for ( User user : friendsList) {
            friends.add(user);
        }
        return friends;
    }

    public ArrayList<User> getFriendsList() {
        return friendsList;
    }

    public User getFriend(int position) {
        return (User) this.get(position);
    }

    public void addFriend( User user) {
        friendsList.add(user);
    }

    public void removeFriend( User user) {
        friendsList.remove(user);
    }

    public void blockFriend( User user) {
        // TODO: Block friend, maybe put in User???
    }
}
