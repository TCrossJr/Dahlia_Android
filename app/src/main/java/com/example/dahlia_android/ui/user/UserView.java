package com.example.dahlia_android.ui.user;

/**
 * Class exposing authenticated user details to the UI.
 */
public class UserView {
    private User _user;
    //... other data fields that may be accessible to the UI

    UserView(User user) {
        this._user = user;
    }

    public UserView(Object user) {
        this._user = new User((User)user);
    }

    User getUser() {
        return _user;
    }
}