package com.example.dahlia_android.ui.nearby;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dahlia_android.R;
import com.example.dahlia_android.api.APIClient;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.ui.recyclerview.MainAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.dahlia_android.data.DataSource.TOKEN;
import static com.example.dahlia_android.data.DataSource.USER_ID;
//import com.example.dahlia_android.ui.nearby.databinding.ActivityAuPairNearByMapBinding;

public class AuPairNearByMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "AuPairNearByMapActivity";
    private GoogleMap mMap;
    private AuPairNearby currentUser;
    private APIServiceInterface apiInterface;

    private AuPairNearbyViewModel auPairNearbyViewModel;
    private RecyclerView rView;
    protected MainAdapter nearby_adapter;
    protected LinearLayoutManager layoutManager;
    private int NEARBY_ID = 3;
//    private AuPairNearbyViewModel auPairNearbyViewModel;
//    private ActivityAuPairNearByMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aupair_nearby);

        auPairNearbyViewModel = new ViewModelProvider(this, new AuPairNearbyViewModelFactory()).get(AuPairNearbyViewModel.class);
//        auPairNearbyViewModel = new ViewModelStore(this, new AuPairNearbyViewModelFactory()).get(AuPairNearbyViewModel.class);
//        binding = ActivityAuPairNearByMapBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        final Button btn_enable = findViewById(R.id.nearby_btn_enable);
        final Button btn_disable = findViewById(R.id.nearby_btn_disable);
        final Button btn_refresh = findViewById(R.id.nearby_btn_refresh);

        rView = findViewById(R.id.nearby_recycler_view);
        layoutManager = new LinearLayoutManager(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_aupair_nearby);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        btn_enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Prompt user if they want to be located.
                DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                // TODO: Yes, Locate me
                                btn_enable.setEnabled(false);
                                btn_disable.setEnabled(true);
//                                createNearbyUser();
                                Log.d(TAG, "createNearby: Nearby user created." );
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                // No Don't
                                dialog.cancel();
                                break;

                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("You are about to ENABLE Dahlia AuPairNearby location services. Are you sure you want to do this?")
                        .setPositiveButton("Yes", dialog)
                        .setNegativeButton("No", dialog).show();
            }
        });
        btn_disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Prompt user if they want to stop being located.
                DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                // TODO: Yes, remove my location
                                btn_enable.setEnabled(true);
                                btn_disable.setEnabled(false);
//                                removeNearbyUser();
                                Log.d(TAG, "removeNearby: Nearby user removed." );
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                // No Don't
                                dialog.cancel();
                                break;

                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("You are about to DISABLE Dahlia AuPairNearby location services. Are you sure you want to do this?")
                        .setPositiveButton("Yes", dialog)
                        .setNegativeButton("No", dialog).show();
            }
        });
/*        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auPairNearbyViewModel.getNearby() == null) {
                    auPairNearbyViewModel.loadNearby();
                    auPairNearbyViewModel.getNearbyResult().observe(AuPairNearByMapActivity.this, new Observer<AuPairNearbyResult>() {
                        @Override
                        public void onChanged(AuPairNearbyResult auPairNearbyResult) {
                            if (auPairNearbyResult == null) {
                                return;
                            }
                            if (auPairNearbyResult.getError() != null) {
                                showNearbyFailed(auPairNearbyResult.getError());
                            }
                            if (auPairNearbyResult.getSuccess() != null) {
                                updateUiWithNearby(auPairNearbyResult.getSuccess());
                            }
                        }
                    });
                }
            }
        });*/
    }

    private void updateUiWithNearby(AuPairNearbyView model) {
        updateUI();
    }

    private void updateUI() {
        nearby_adapter = new MainAdapter(auPairNearbyViewModel.getNearby());
        nearby_adapter.onAttachedToRecyclerView(rView);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        nearby_adapter.notifyDataSetChanged();
        rView.setAdapter(nearby_adapter);
    }

    private void showNearbyFailed(Integer error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private void createNearbyUser() {
        try {
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);
            double latitude = 44.08444;
            double longitude = -73.35694;
            Call<AuPairNearby> createCall = apiInterface.createNearby(TOKEN, USER_ID, true, latitude, longitude); // TODO: hardcoded token
            Response<AuPairNearby> response = createCall.execute();
            Log.d(TAG, "createNearby: " + response.message() );
            if(response.isSuccessful()) {
                Toast.makeText(this, R.string.prompt_nearby_created, Toast.LENGTH_LONG);
//                rvService.removeItem(getAdapterPosition());
            } else {
                DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {// Yes, create AuPairNearby
                            dialog.cancel();
                            Log.d(TAG, "CreateNearby: Error creating Nearby.");
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Error creating AuPairNearby. Try again later.")
                        .setPositiveButton("OK", dialog).show();
            }
            Log.d(TAG, "createNearby: " + response.message() );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeNearbyUser() {
        try {
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);
            Call<Void> removeCall = apiInterface.removeNearby(TOKEN, NEARBY_ID); // TODO: hardcoded token
            Response<Void> response = removeCall.execute();
            Log.d(TAG, "removeNearby: " + response.message() );
            if(response.isSuccessful()) {
                Toast.makeText(this, R.string.prompt_nearby_removed, Toast.LENGTH_LONG);
//                rvService.removeItem(getAdapterPosition());
            } else {
                DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {// Yes, create AuPairNearby
                            dialog.cancel();
                            Log.d(TAG, "RemoveNearby: Error removing Nearby.");
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Error removing AuPairNearby. Try again later.")
                        .setPositiveButton("OK", dialog).show();
            }
            Log.d(TAG, "removeNearby: " + response.message() );
        } catch (Exception e) {
            e.printStackTrace();
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