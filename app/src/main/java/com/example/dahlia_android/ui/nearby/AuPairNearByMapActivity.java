package com.example.dahlia_android.ui.nearby;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.dahlia_android.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.example.dahlia_android.ui.nearby.databinding.ActivityAuPairNearByMapBinding;

public class AuPairNearByMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private AuPairNearby currentUser;
//    private AuPairNearbyViewModel auPairNearbyViewModel;
    private GoogleMap mMap;
//    private ActivityAuPairNearByMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aupair_nearby);
//        binding = ActivityAuPairNearByMapBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_aupair_nearby);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        LatLng userLocation =new LatLng(currentUser.getLatitude(), currentUser.getLongitude());
        LatLng userLocation =new LatLng(Double.parseDouble("44.07444"), Double.parseDouble("-73.34694"));
//        mMap.addMarker(new MarkerOptions().position(userLocation).title(String.valueOf(currentUser.getUserID())));
        mMap.addMarker(new MarkerOptions().position(userLocation).title(String.valueOf("Title")));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
        mMap.moveCamera(CameraUpdateFactory.zoomTo((float)7.5));
    }
}