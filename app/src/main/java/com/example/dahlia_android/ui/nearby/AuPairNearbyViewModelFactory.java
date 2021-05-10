package com.example.dahlia_android.ui.nearby;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.DataSource;
import com.example.dahlia_android.ui.friends.FriendsViewModel;

/**
 * ViewModel provider factory to instantiate AuPairNearbyViewModel.
 * Required given AuPairNearbyViewModel has a non-empty constructor
 */
public class AuPairNearbyViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AuPairNearbyViewModel.class)) {
            return (T) new FriendsViewModel(DataRepository.getInstance(new DataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}