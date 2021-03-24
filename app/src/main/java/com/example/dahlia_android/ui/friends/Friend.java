package com.example.dahlia_android.ui.friends;

public class Friend {

    // UserProfile friendsProfile
    private String friendUsername;
    private String friendProfileImageURL;
    private String friendDisplayName;

    public Friend(String friendProfileImageURL, String friendDisplayName, String friendUsername) {
        this.friendProfileImageURL = friendProfileImageURL;
        this.friendDisplayName = friendDisplayName;
        this.friendUsername = friendUsername;
    }

    public String getFriendProfileImageURL() {
        return friendProfileImageURL;
    }

    public void setFriendProfileImageURL(String friendProfileImageURL) {
        this.friendProfileImageURL = friendProfileImageURL;
    }

    public String getFriendDisplayName() {
        return friendDisplayName;
    }

    public void setFriendDisplayName(String friendDisplayName) {
        this.friendDisplayName = friendDisplayName;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }
}
