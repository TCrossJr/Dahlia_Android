package com.example.dahlia_android.data;

import android.util.Log;

import com.example.dahlia_android.ApplicationUser;
import com.example.dahlia_android.api.APIClient;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.data.model.LoggedInUser;
import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.groups.Groups;
import com.example.dahlia_android.ui.home.Feed;
import com.example.dahlia_android.ui.home.Post;
import com.example.dahlia_android.ui.messages.Conversations;
import com.example.dahlia_android.ui.messages.Message;
import com.example.dahlia_android.ui.messages.Messages;
import com.example.dahlia_android.ui.nearby.NearbyUsers;
import com.example.dahlia_android.ui.user.User;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Class that handles data and retrieves user information.
 */
public class DataSource {


    private static final String TAG = "DataSource";
    public static final String TOKEN = "4c82f31197fb2300a90b13de623c8d335854037a";
    public static final int USER_ID = 148;
    private APIServiceInterface apiInterface;

    // TODO: The app is using cleartext enabled right now because https:// not implemented yet on server
    public Result<User> login(final String username, final String password) {

        try {
            /* handle loggedInUser authentication */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);

            //@Post username and pw, get token
            String credentials = Credentials.basic(username, password);
            Call<LoggedInUser> callUser = apiInterface.getUserCredentials(
                    credentials, username, password);
            Response<LoggedInUser> responseLogged = callUser.execute();
            LoggedInUser user = responseLogged.body();

            String token = user.getUserToken();

            //Load User
            Call<User> load = apiInterface.getUser(token,user.getUserId());
            Response<User> response = load.execute();
            User loadedUser = response.body();
            Log.d(TAG, "loadUser: User loaded." + response.message());
            ApplicationUser.setCurrentUser(loadedUser);

            return new Result.Success<>(loadedUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        try {
            // TODO: token is empty right now. Need to retrieve
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);
            Response<String> logout = apiInterface.logout("").execute();
            Log.d(TAG, "logout: Signed Out" + logout.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Result<FriendsList> loadFriends() {

        try {
            /* handle loading friendsList */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);
            // TODO: hardcoded token and userID

            //Load Friends
            Call<FriendsList> callFriends = apiInterface.getFriends(TOKEN, USER_ID);
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

            Log.d(TAG, "loadFriends: Friends stored." + response.toString());
            return new Result.Success<>(newFriends);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(new IOException("Error loading Friends", e));
        }
    }

    public Result<Conversations> loadMessages() {

        try {
            /* handle loading messages */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);

            // TODO: hardcoded token and userID
            //Load Conversations
            Call<ArrayList<Messages>> callMessages = apiInterface.getMessages(TOKEN, USER_ID);
            Response<ArrayList<Messages>> response = callMessages.execute();
            ArrayList<Messages> rawMessages = response.body();
            Conversations newMessages = new Conversations();

            for ( Messages msgs : rawMessages) {
                Messages messages = new Messages();
                for( Object msg : msgs ) {
                    Message message = new Message((LinkedTreeMap) msg);
                    messages.add(message);
                }
                newMessages.add((Messages)messages);
            }

            Log.d(TAG, "loadMessages: Conversations loaded." + response.toString());
            return new Result.Success<>(newMessages);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(new IOException("Error loading Friends", e));
        }
    }

    public Result<Feed> loadFeed() {
        try {
            /* handle loading homeFeed */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);

            // TODO: hardcoded token and userID
            //Load Friends
//            Call<Feed> callFeed = apiInterface.getFeed(TOKEN);
            Call<Feed> callFeed = apiInterface.getFeed(TOKEN, USER_ID);
            Response<Feed> response = callFeed.execute();
//            Feed newFeed = (Feed)response.body();
            Feed rawFeed = (Feed) response.body();

            // convert and reconvert to correct type(FriendsList)...
            Gson gson = new Gson();
            String json = gson.toJson(rawFeed, Feed.class);
//            Type feedType = new TypeToken<ArrayList<Post>>(){}.getType();
            Type feedType = new TypeToken<Feed>(){}.getType();
            Feed posts = gson.fromJson(json, feedType);
//            ArrayList<Post> posts = gson.fromJson(json, feedType);
            Feed newFeed = new Feed();
            for ( Object rawPost : posts) {
                Post post = new Post((LinkedTreeMap) rawPost);
                newFeed.add(post);
            }
            Log.d(TAG, "loadFeed: Feed loaded." + response.message());
            return new Result.Success<>(newFeed);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(new IOException("Error loading Feed", e));
        }
    }


    public Result<NearbyUsers> loadNearby() {
        try {
            /* handle loading nearby */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);

            // TODO: hardcoded token and userID
            //Load nearby
//            Call<Feed> callFeed = apiInterface.getFeed(TOKEN);
            Call<NearbyUsers> callFeed = apiInterface.getNearby(TOKEN);
            Response<NearbyUsers> response = callFeed.execute();
//            Feed newFeed = (Feed)response.body();
            NearbyUsers newNearby = (NearbyUsers) response.body();
/*
            // convert and reconvert to correct type(FriendsList)...
            Gson gson = new Gson();
            String json = gson.toJson(rawFeed, Feed.class);
//            Type feedType = new TypeToken<ArrayList<Post>>(){}.getType();
            Type feedType = new TypeToken<Feed>(){}.getType();
            Feed posts = gson.fromJson(json, feedType);
//            ArrayList<Post> posts = gson.fromJson(json, feedType);
            Feed newFeed = new Feed();
            for ( Object rawPost : posts) {
                Post post = new Post((LinkedTreeMap) rawPost);
                newFeed.add(post);
            }
            */
            Log.d(TAG, "loadNearby: Nearby loaded." + response.message());
            return new Result.Success<>(newNearby);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(new IOException("Error loading Nearby Users", e));
        }
    }

    public Result<User> loadUser() {
        return null;
    }

    public Result<Groups> loadGroups() {
        try {
            /* handle loading groups */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);

            // TODO: hardcoded token and userID
            //Load Groups
//            Call<Groups> callGroups = apiInterface.getGroups(TOKEN);
            Call<Groups> callGroups = apiInterface.getGroups(TOKEN, USER_ID);
            Response<Groups> response = callGroups.execute();
            Groups newGroups = (Groups) response.body();
            Log.d(TAG, "loadGroups: Groups loaded." + response.message());
            return new Result.Success<>(newGroups);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(new IOException("Error loading Groups.", e));
        }
    }
}
