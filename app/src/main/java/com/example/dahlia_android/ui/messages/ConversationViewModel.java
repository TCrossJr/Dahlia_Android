package com.example.dahlia_android.ui.messages;

import androidx.lifecycle.ViewModel;

import com.example.dahlia_android.data.DataRepository;

public class ConversationViewModel extends ViewModel {

    private DataRepository dataRepository;

    public ConversationViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public Messages getConversation(int position) {
        return this.dataRepository.getConversation(position);
    }
}
