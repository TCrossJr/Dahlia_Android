package com.example.dahlia_android.ui.user;

import java.util.ArrayList;

public class UserList extends ArrayList {

    private ArrayList<User> userList;

    public UserList(){
        userList = new ArrayList<>();
    }

    public UserList(UserList users) {
        userList = users;
    }

    public UserList(ArrayList<User> users){
        userList = convertArrayList(users);
    }

    private ArrayList<User> convertArrayList(ArrayList<User> users) {
        UserList usrs = new UserList();
        for ( User user: userList) {
            usrs.add(user);
        }
        return usrs;
    }
    public ArrayList<User> getUserList(){
        return userList;
    }

    public User getUser(int postion) {
        return (User) this.get(postion);
    }
}
