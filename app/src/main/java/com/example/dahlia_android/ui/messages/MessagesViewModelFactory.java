package com.example.dahlia_android.ui.messages;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.DataSource;

/**
 * ViewModel provider factory to instantiate MessagesViewModel.
 * Required given MessagesViewModel has a non-empty constructor
 */
public class MessagesViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MessagesViewModel.class)) {
            return (T) new MessagesViewModel(DataRepository.getInstance(new DataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}