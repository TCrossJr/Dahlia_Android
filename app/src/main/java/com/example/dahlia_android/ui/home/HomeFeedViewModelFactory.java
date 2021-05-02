package com.example.dahlia_android.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.DataSource;

/**
 * ViewModel provider factory to instantiate HomeFeedViewModel.
 * Required given HomeFeedViewModel has a non-empty constructor
 */
public class HomeFeedViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeFeedViewModel.class)) {
            return (T) new HomeFeedViewModel(DataRepository.getInstance(new DataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}