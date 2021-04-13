package com.example.dahlia_android.ui.nearby;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dahlia_android.R;

public class AuPairNearByFragment extends Fragment {

    private AuPairNearByViewModel mViewModel;

    public static AuPairNearByFragment newInstance() {
        return new AuPairNearByFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.au_pair_nearby_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AuPairNearByViewModel.class);
        // TODO: Use the ViewModel
    }

}