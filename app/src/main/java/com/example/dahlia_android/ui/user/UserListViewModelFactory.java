package com.example.dahlia_android.ui.user;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.DataSource;

import org.jetbrains.annotations.NotNull;

public class UserListViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserListViewModel.class)) {
            return (T) new UserListViewModel(DataRepository.getInstance(new DataSource()));
        } else {
            throw new IllegalArgumentException("Unknown Viewmodel class");
        }
    }
}
