package com.example.dahlia_android.ui.user;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.MainActivity;
import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.recyclerview.AdapterTypeList;
import com.example.dahlia_android.ui.recyclerview.MainAdapter;

public class UserProfileActivity extends AppCompatActivity {

    private UserProfileViewModel userProfileViewModel;
    protected MainAdapter profile_adapter;
    protected AdapterTypeList adapterTypeList;
    protected LinearLayoutManager layoutManager;
    private RecyclerView rView;
    public static UserProfile current_profile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_profile);
        userProfileViewModel =
                new ViewModelProvider(this).get(UserProfileViewModel.class);
        rView = findViewById(R.id.profile_combined_recycler);
        layoutManager = new LinearLayoutManager(this);
        profile_adapter = new MainAdapter(MainActivity._user_profile, adapterTypeList); //TODO: Change or move to Data class somehow from MainActivity
//        profile_adapter = new MainAdapter(MainActivity._messageList, adapterTypeList); //TODO: Change or move to Data class somehow from MainActivity
        profile_adapter.notifyDataSetChanged();
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        rView.setAdapter(profile_adapter);
    }
}
