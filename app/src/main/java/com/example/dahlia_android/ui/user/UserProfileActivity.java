package com.example.dahlia_android.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.MainActivity;
import com.example.dahlia_android.R;

public class UserProfileActivity extends AppCompatActivity {

    private UserProfileViewModel userProfileViewModel;
    protected UserProfileDataAdapter profile_adapter;
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
        profile_adapter = new UserProfileDataAdapter(MainActivity._user_profile); //TODO: Change or move to Data class somehow from MainActivity
        profile_adapter.notifyDataSetChanged();
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        rView.setAdapter(profile_adapter);
    }
/*

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userProfileViewModel =
                new ViewModelProvider(this).get(UserProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_user_profile, container, false);
        rView = root.findViewById(R.id.profile_combined_recycler);
        layoutManager = new LinearLayoutManager(getActivity());
        profile_adapter = new UserProfileDataAdapter(MainActivity._user_profile); //TODO: Change or move to Data class somehow from MainActivity
        profile_adapter.notifyDataSetChanged();
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        rView.setAdapter(profile_adapter);
        return root;
    }
*/
}
