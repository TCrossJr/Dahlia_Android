package com.example.dahlia_android.ui.user;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.Result;

public class UserListViewModel extends ViewModel {
    private MutableLiveData<UserListResult> userListResult = new MutableLiveData<UserListResult>();
    private DataRepository dataRepository;

    public UserListViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void loadUserList() {
        Result<UserList> userListResult =dataRepository.loadUserList();

        if(userListResult instanceof Result.Success) {
            UserList data = ((Result.Success<UserList>) userListResult).getData();
            this.userListResult.setValue(new UserListResult(new UserListView((data.getUserList()))));
        } else {
            this.userListResult.setValue(new UserListResult("Loading UserList failed"));
        }
    }

    public UserList getUserList() {
        return this.dataRepository.getUserList();

    }
    public User getUser_fromList(int userID){
        return this.dataRepository.getFriendByID(userID);
    }
    public int getUserID_fromList() {
        return dataRepository.getUser().getUserID();
    }
    public User getUser(){
        return dataRepository.getUser();
    }
}
