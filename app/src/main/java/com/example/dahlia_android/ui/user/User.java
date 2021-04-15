package com.example.dahlia_android.ui.user;

public class User {

    private UserProfile userProfile;
    private UserProfileCombinedList profileCombined;
    private String username;
    private String displayname;

    public User(UserProfile userProfile, UserProfileCombinedList userProfileCombinedList ) {
        this.userProfile = userProfile;
        this.profileCombined = userProfileCombinedList;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public UserProfileCombinedList getProfileCombined() {
        return profileCombined;
    }
}
