package com.example.dahlia_android.ui.nearby;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.dahlia_android.R;

public class AuPairNearbyFragment extends Fragment {


    public static AuPairNearbyFragment newInstance() {
        return new AuPairNearbyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.au_pair_nearby_fragment, container, false);
//        mViewModel = new ViewModelProvider(this).get(AuPairNearbyViewModel.class);

        final Button findNearby = view.findViewById(R.id.nearby_btn_find);
        final ProgressBar loading = view.findViewById(R.id.nearby_loading);


        findNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                Intent intent = new Intent(v.getContext(), AuPairNearByMapActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}