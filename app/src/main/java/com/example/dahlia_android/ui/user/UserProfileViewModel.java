package com.example.dahlia_android.ui.user;

import android.view.View;
import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserProfileViewModel extends ViewModel {

    public static UserProfile currentProfile;
    private MutableLiveData<String> mText;

    public UserProfileViewModel() {
    }

    public void goToProfileUser(View view, User user) {
        Button openProfile = (Button) view.getTag();
//        openProfile.setOnClickListener(v -> );
    }

    public LiveData<String> getText() {
        return mText;
    }
}