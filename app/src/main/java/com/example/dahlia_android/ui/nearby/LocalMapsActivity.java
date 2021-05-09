package com.example.dahlia_android.ui.nearby;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.dahlia_android.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class LocalMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnCameraChangeListener {

    private GoogleMap mMap;
//    Marker[] markerArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.local_map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Detect zoom level change
        mMap.setOnCameraChangeListener(this);

        // Move the camera to focus on Vermont
        LatLng vermont = new LatLng(44.5588, -72.5778);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vermont));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(7));
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition){
/*        // Make markers invisible below zoom level 9
        for(int i = 0; i < 251; i++){
            markerArray[i].setVisible(cameraPosition.zoom > 9);
        }*/
    }
}