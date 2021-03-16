package com.example.dahlia_android.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    @SerializedName("user_id")
    private String userId;

    @SerializedName("email")
    private String userEmail;

    @SerializedName("token")
    private String userToken;

    public LoggedInUser(String userId, String userEmail, String userToken) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userToken = userToken;
    }

    public LoggedInUser( LoggedInUser user) {
        this.userId = user.userId;
        this.userEmail = user.userEmail;
        this.userToken = user.userToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}