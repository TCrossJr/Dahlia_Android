package com.example.dahlia_android.ui.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserProfileViewModel extends ViewModel {

    public static UserProfile currentProfile;
    private MutableLiveData<String> mText;

    public UserProfileViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }
}