package com.example.dahlia_android.ui.nearby;

import java.util.ArrayList;

/**
 * Class exposing authenticated nearbyUsers details to the UI.
 */
class AuPairNearbyView {
    private NearbyUsers _nearbyUsers;
    //... other data fields that may be accessible to the UI

    AuPairNearbyView(NearbyUsers nearbyUsers) {
        this._nearbyUsers = nearbyUsers;
    }

    AuPairNearbyView(ArrayList<AuPairNearby> nearbyUser) {
        this._nearbyUsers = new NearbyUsers(nearbyUser);
    }

    public NearbyUsers getNearbyUsers() {
        return _nearbyUsers;
    }
}