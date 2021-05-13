package com.example.dahlia_android.ui.user;

import java.util.ArrayList;

public class UserListView {
    private UserList _userList;

    UserListView(UserList userList) {
        this._userList = userList;
    }

    UserListView(ArrayList<User> userList){
        this._userList = new UserList(userList);
    }

    UserList getUserList(){
        return _userList;
    }

}
