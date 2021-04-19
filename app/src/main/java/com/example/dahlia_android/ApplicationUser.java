package com.example.dahlia_android;

import android.app.Application;

import com.example.dahlia_android.ui.user.User;

public class ApplicationUser extends Application {
    static User current_user;

    public static User getCurrentUser() {
        return current_user;
    }

    public static void setCurrentUser(User current_user) {
        ApplicationUser.current_user = current_user;
    }
}
