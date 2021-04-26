package com.example.dahlia_android.ui.user;

import com.google.gson.annotations.SerializedName;

public class UserProfile {

    private String bannerURL;
    private String thumbnailURL;
    private String displayName;
    private String username; // email
    @SerializedName("date_created")
    private String date_created;

    @SerializedName("date_of_birth")
    private String date_of_birth;

    @SerializedName("town")
    private String town;

    @SerializedName("state")
    private String state;

    @SerializedName("zip_code")
    private String zip_code;

    @SerializedName("description")
    private String description;


    public UserProfile(String bannerURL, String thumbnailURL, String displayName, String username, String date_created, String date_of_birth, String town, String state, String zipCode, String description) {
        this.bannerURL = bannerURL;
        this.thumbnailURL = thumbnailURL;
        this.displayName = displayName;
        this.username = username;
        this.date_created = date_created;
        this.date_of_birth = date_of_birth;
        this.town = town;
        this.state = state;
        this.zip_code = zipCode;
        this.description = description;
    }

    public UserProfile(UserProfile profile) {
        this.bannerURL = profile.bannerURL;
        this.thumbnailURL = profile.thumbnailURL;
        this.displayName = profile.displayName;
        this.username = profile.username;
        this.date_created = profile.date_created;
        this.date_of_birth = profile.date_of_birth;
        this.town = profile.town;
        this.state = profile.state;
        this.zip_code = profile.zip_code;
        this.description = profile.description;
    }

    public UserProfile(String displayName, String username) {
        this.bannerURL = "bannerURL";
        this.displayName = displayName;
        this.username = username;
        this.date_created = "date_created";
        this.date_of_birth = "date_of_birth";
        this.town = "town";
        this.state = "state";
        this.zip_code = "zip_code";
        this.description = "description";
    }

    // TODO: maybe change to setter method that updates only these fields
    public UserProfile(String date_of_birth, String town, String state, String zipCode, String description) {
        this.date_of_birth = date_of_birth;
        this.town = town;
        this.state = state;
        this.zip_code = zipCode;
        this.description = description;
    }

    public UserProfile(String username, String displayName, String date_of_birth, String town, String state, String zipCode, String description) {
        this.username = username;
        this.displayName = displayName;
        this.date_of_birth = date_of_birth;
        this.town = town;
        this.state = state;
        this.zip_code = zipCode;
        this.description =description;
    }

    public String getBannerURL() {
        return bannerURL;
    }

    public void setBannerURL(String bannerURL) {
        this.bannerURL = bannerURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }
}
