package com.example.dahlia_android.ui.nearby;

import com.google.gson.annotations.SerializedName;

public class AuPairNearby {

    @SerializedName("id") private int nearbyID;
    @SerializedName("date_created") private String dateCreated;
    @SerializedName("enabled") private boolean enabled;
    @SerializedName("latitude") private Double latitude;
    @SerializedName("longitude") private Double longitude;
    @SerializedName("user") private int userID;

    public AuPairNearby(int nearbyID, String dateCreated, boolean enabled, Double latitude, Double longitude, int userID) {
        this.nearbyID = nearbyID;
        this.dateCreated = dateCreated;
        this.enabled = enabled;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userID = userID;
    }

    public int getNearbyID() {
        return nearbyID;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getUserID() {
        return userID;
    }
}
