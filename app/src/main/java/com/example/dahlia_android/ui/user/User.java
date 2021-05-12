package com.example.dahlia_android.ui.user;

import androidx.annotation.NonNull;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.messages.Messages;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import org.jetbrains.annotations.NotNull;

public class User {

    @SerializedName("id") private int userID;
    @SerializedName("username") private String username;
    @SerializedName("email") private String email;
    @SerializedName("first_name") private String firstName;
    @SerializedName("last_name") private String lastName;
    @SerializedName("agency") private String agency;
    @SerializedName("created_dt") private String created_dt;
    @SerializedName("profile") private UserProfile userProfile;

    private int followingCount;
    private int followerCount;

    public User(int userID, String username, String email, String firstName, String lastName, String agency, String created_dt, UserProfile userProfile, int followingCount, int followerCount) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.agency = agency;
        this.created_dt = created_dt;
        this.userProfile = userProfile;
        this.followingCount = followingCount;
        this.followerCount = followerCount;
    }

    public User(User user) {
        this.userID = user.userID;
        this.username = user.username;
        this.email = user.email;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.agency = user.agency;
        this.created_dt = user.created_dt;
        this.userProfile = user.userProfile;
        this.followingCount = user.followingCount;
        this.followerCount = user.followerCount;
    }

    //TODO: RMV
    public User() {
        this.userProfile = new UserProfile("testName1", "testUser1");
        this.followingCount = 0;
        this.followerCount = 0;
    }

    public User(LinkedTreeMap user) {
        double id = Double.parseDouble(String.valueOf(user.get("id")));
        this.userID = (int) id;
        this.username = (String) user.get("username");
        this.email = (String) user.get("email");
        this.firstName = (String) user.get("first_name");
        this.lastName = (String) user.get("last_name");
        this.agency = (String) user.get("agency");
//        this.userProfile = (String) user.get("profile");
    }

    public User(int id, String username, String email, String first_name, String last_name, String agency) {
        this.userID = id;
        this.username = username;
        this.email = email;
        this.firstName = first_name;
        this.lastName = last_name;
        this.agency = agency;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAgency() {
        return agency;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public User getUser() {
        return this;
    }



    @Override
    public String toString() {
        return this.username;
    }
}
