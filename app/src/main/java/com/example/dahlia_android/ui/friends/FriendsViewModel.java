package com.example.dahlia_android.ui.friends;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahlia_android.R;
import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.Result;
import com.example.dahlia_android.ui.user.User;

public class FriendsViewModel extends ViewModel {

    private MutableLiveData<FriendsResult> friendsResult = new MutableLiveData<FriendsResult>();
    private DataRepository dataRepository;

    public FriendsViewModel(DataRepository dataRepository) { this.dataRepository = dataRepository; }

    LiveData<FriendsResult> getFriendsResult() { return friendsResult; }

    public void loadFriends() {
        Result<FriendsList> friendsResult = dataRepository.loadFriends();

        if(friendsResult instanceof Result.Success) {
            FriendsList data = ((Result.Success<FriendsList>) friendsResult).getData();
            this.friendsResult.setValue(new FriendsResult(new FriendsView((data.getFriendsList()))));
        } else {
            this.friendsResult.setValue(new FriendsResult(R.string.prompt_friendslist_failed));
        }
    }

    public FriendsList getFriends() {
        // TODO: Uncomment and remove return statement
//        return this.dataRepository.getFriends();
        return new FriendsList();
    }

    public User getFriend(int userID) {
        return dataRepository.getFriendByID(userID);
    }
}