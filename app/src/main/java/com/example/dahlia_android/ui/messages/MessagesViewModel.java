package com.example.dahlia_android.ui.messages;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahlia_android.R;
import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.Result;
import com.example.dahlia_android.ui.user.User;

public class MessagesViewModel extends ViewModel {

    private MutableLiveData<MessagesResult> messagesResult = new MutableLiveData<MessagesResult>();
    private DataRepository dataRepository;

    MessagesViewModel(DataRepository dataRepository) { this.dataRepository = dataRepository; }

    public LiveData<MessagesResult> getMessagesResult() { return messagesResult; }

    public void loadMessages() {
        Result<Conversations> messagesResult = dataRepository.loadConversations();

        if(messagesResult instanceof Result.Success) {
            Conversations data = ((Result.Success<Conversations>) messagesResult).getData();
            this.messagesResult.setValue(new MessagesResult(new MessagesView((data.getConversations()))));
        } else {
            this.messagesResult.setValue(new MessagesResult(R.string.prompt_messages_failed));
        }
    }

    public Conversations getConversations() {
        return this.dataRepository.getMessages();
    }

    public User getUser() {
        return dataRepository.getUser();
    }
}