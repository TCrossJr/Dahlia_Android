package com.example.dahlia_android.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahlia_android.R;
import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.Result;
import com.example.dahlia_android.ui.user.User;

public class HomeFeedViewModel extends ViewModel {

    private MutableLiveData<HomeFeedResult> homeFeedResult = new MutableLiveData<HomeFeedResult>();
    private DataRepository dataRepository;

    public HomeFeedViewModel(DataRepository dataRepository) { this.dataRepository = dataRepository; }

    LiveData<HomeFeedResult> getHomeFeedResult() { return homeFeedResult; }

    public LiveData<HomeFeedResult> getFeedResult() {
        return homeFeedResult;
    }

    public void loadFeed() {
        Result<Feed> feedResult = dataRepository.loadFeed();

        if(feedResult instanceof Result.Success) {
            Feed data = ((Result.Success<Feed>) feedResult).getData();
            this.homeFeedResult.setValue(new HomeFeedResult(new HomeFeedView((data.getHomeFeed()))));
        } else {
            this.homeFeedResult.setValue(new HomeFeedResult(R.string.prompt_homefeed_failed));
        }
    }

    public Feed getFeed() {
        return this.dataRepository.getFeed();
    }

    public User getFriend(int userID) {
        return dataRepository.getFriendByID(userID);
    }

    public User getUser() {
        return dataRepository.getUser();
    }
}