package com.example.dahlia_android.ui.groups;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahlia_android.R;
import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.Result;
import com.example.dahlia_android.ui.messages.Conversations;
import com.example.dahlia_android.ui.messages.MessagesResult;
import com.example.dahlia_android.ui.user.User;

public class GroupsViewModel extends ViewModel {

    private MutableLiveData<GroupsResult> groupsResult = new MutableLiveData<>();
    private DataRepository dataRepository;

    GroupsViewModel(DataRepository dataRepository) { this.dataRepository = dataRepository; }

    public LiveData<GroupsResult> getGroupsResult() { return groupsResult; }

    public void loadGroups() {
        Result<Groups> groupsResult = dataRepository.loadGroups();

        if(groupsResult instanceof Result.Success) {
            Groups data = ((Result.Success<Groups>) groupsResult).getData();
            this.groupsResult.setValue(new GroupsResult(new GroupsView((data.getGroups()))));
        } else {
            this.groupsResult.setValue(new GroupsResult(R.string.prompt_groups_failed));
        }
    }

    public Groups getGroups() {
        return this.dataRepository.getGroups();
    }

    public User getUser() {
        return dataRepository.getUser();
    }
}