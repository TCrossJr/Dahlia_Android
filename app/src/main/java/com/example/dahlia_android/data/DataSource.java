package com.example.dahlia_android.data;

import android.util.Log;

import com.example.dahlia_android.api.APIClient;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.user.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Class that handles data and retrieves user information.
 */
public class DataSource {

    private static final String TAG = "DataSource";
    private APIServiceInterface apiInterface;

    // TODO: The app is using cleartext enabled right now because https:// not implemented yet on server
    public Result<FriendsList> loadFriends() {

        try {
            /* handle loading friendsList */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);
            // TODO: hardcoded token
            String token = "4c82f31197fb2300a90b13de623c8d335854037a";

            //Load Friends
            Call<FriendsList> callFriends = apiInterface.getFriends(token, 148);
            Response<FriendsList> response = callFriends.execute();
            FriendsList rawFriendsList = (FriendsList) response.body();

            // convert and reconvert to correct type(FriendsList)
            Gson gson = new Gson();
            String json = gson.toJson(rawFriendsList);
            Type friendsType = new TypeToken<ArrayList<User>>(){}.getType();
            ArrayList<User> friends = gson.fromJson(json, friendsType);
            FriendsList newFriends = new FriendsList();
            for ( User user : friends) {
                newFriends.add(user);
            }

            // store friends
//            ApplicationUser.setFriendsList(newFriends); // TODO: change to ViewModel, DataSource, and DataRepository
            Log.d(TAG, "loadFriends: Friends stored." + newFriends.toString());

            return new Result.Success<>(newFriends);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(new IOException("Error loading Friends", e));
        }
    }
}