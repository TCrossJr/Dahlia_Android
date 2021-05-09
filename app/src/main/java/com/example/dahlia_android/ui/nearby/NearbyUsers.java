package com.example.dahlia_android.ui.nearby;

import java.util.ArrayList;

public class NearbyUsers extends ArrayList<Object> {
    private ArrayList<AuPairNearby> nearbyUsers;

    public NearbyUsers(ArrayList<AuPairNearby> nearbyUsers) {
        this.nearbyUsers = nearbyUsers;
    }

    public NearbyUsers() {
        this.nearbyUsers = new ArrayList<>();
    }

    public ArrayList<AuPairNearby> getNearbyUsers() {
        return nearbyUsers;
    }
    public int getNearbyUserID(AuPairNearby nearby) {
        return nearby.getUserID();
    }
}
