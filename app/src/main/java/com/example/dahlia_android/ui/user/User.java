package com.example.dahlia_android.ui.user;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id") private int id;
    @SerializedName("username") private String username;
    @SerializedName("email") private String email;
    @SerializedName("first_name") private String firstName;
    @SerializedName("last_name") private String lastName;
    @SerializedName("agency") private String agency;
    @SerializedName("created_dt") private String created_dt;
    @SerializedName("profile") private UserProfile userProfile;

    private int followingCount;
    private int followerCount;

    public User(int id, String username, String email, String firstName, String lastName, String agency, String created_dt, UserProfile userProfile, int followingCount, int followerCount) {
        this.id = id;
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
        this.id = user.id;
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

    public int getUserID() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public User getUser() {
        return this;
    }
}
