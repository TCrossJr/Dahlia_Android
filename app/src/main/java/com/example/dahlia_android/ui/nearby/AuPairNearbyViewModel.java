package com.example.dahlia_android.ui.nearby;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dahlia_android.R;
import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.Result;
import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.user.User;

import java.util.ArrayList;

public class AuPairNearbyViewModel {

    private MutableLiveData<AuPairNearbyResult> nearbyResult = new MutableLiveData<AuPairNearbyResult>();
    private DataRepository dataRepository;

    public AuPairNearbyViewModel(DataRepository dataRepository) { this.dataRepository = dataRepository; }

    public LiveData<AuPairNearbyResult> getNearbyResult() { return nearbyResult; }

    public void loadNearby() {
        Result<NearbyUsers> nearbyResult = dataRepository.loadNearby();

        if(nearbyResult instanceof Result.Success) {
            NearbyUsers data = ((Result.Success<NearbyUsers>) nearbyResult).getData();
            this.nearbyResult.setValue(new AuPairNearbyResult(new AuPairNearbyView((data.getNearbyUsers()))));
        } else {
            this.nearbyResult.setValue(new AuPairNearbyResult(R.string.prompt_nearby_users_failed));
        }
    }

    public int getMyID() {
        return dataRepository.getUser().getUserID();
    }

    public User getUser() {
        return dataRepository.getUser();
    }

    public NearbyUsers getNearby() {
        return this.dataRepository.getNearbyUsers();
    }
}