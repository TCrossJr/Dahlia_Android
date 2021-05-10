package com.example.dahlia_android.ui.groups;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.DataSource;

/**
 * ViewModel provider factory to instantiate GroupsViewModel.
 * Required given GroupsViewModel has a non-empty constructor
 */
public class GroupsViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GroupsViewModel.class)) {
            return (T) new GroupsViewModel(DataRepository.getInstance(new DataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}