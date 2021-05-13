package com.example.dahlia_android.data;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.dahlia_android.R;
import com.example.dahlia_android.api.APIClient;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.data.model.LoggedInUser;
import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.groups.Groups;
import com.example.dahlia_android.ui.home.CreatePostActivity;
import com.example.dahlia_android.ui.home.Feed;
import com.example.dahlia_android.ui.home.Post;
import com.example.dahlia_android.ui.messages.Conversations;
import com.example.dahlia_android.ui.messages.Message;
import com.example.dahlia_android.ui.messages.Messages;
import com.example.dahlia_android.ui.nearby.NearbyUsers;
import com.example.dahlia_android.ui.user.User;
import com.example.dahlia_android.ui.user.UserList;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that handles data and retrieves user information.
 */
public class DataSource {


    private static final String TAG = "DataSource";
        public static int NEARBY_ID = 3; // TODO: RMV/Not finished

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
            if(user!=null) {
                DataRepository.getInstance(this).setTokenString(user.getUserToken());
            }
            //Load User
            Call<User> load = apiInterface.getUser(user.getUserToken(), user.getUserId());
            Response<User> response = load.execute();
            User loadedUser = response.body();
            Log.d(TAG, "loadUser: User loaded." + response.message());
//            ApplicationUser.setCurrentUser(loadedUser);

            return new Result.Success<>(loadedUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout(String token) {
        try {
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);
            Response<String> logout = apiInterface.logout(token).execute();
            Log.d(TAG, "logout: Signed Out" + logout.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Result<UserList> loadUserList(String token, int userID) {

        try {
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);
            //Load UserList
            Call<UserList> callUserList = apiInterface.getUserList(token, userID);
            Response<UserList> response = callUserList.execute();
            UserList rawUserList = (UserList) response.body();

            Gson gson = new Gson();
            String json = gson.toJson(rawUserList);
            Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
            ArrayList<User> userList = gson.fromJson(json, userListType);
            UserList newUserList = new UserList();
            for (User user: userList) {
                newUserList.add(user);
            }
            return new Result.Success<>(newUserList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(new IOException("Error loading UserList", e));
        }
    }

    public Result<FriendsList> loadFriends(String token, int userID) {

        try {
            /* handle loading friendsList */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);

            //Load Friends
            Call<FriendsList> callFriends = apiInterface.getFriends(token, userID);
            Response<FriendsList> response = callFriends.execute();
            FriendsList rawFriendsList = (FriendsList) response.body();

            // TODO: Change to Deserializer
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

    public Result<Conversations> loadMessages(String token, int userID) {

        try {
            /* handle loading messages */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);

            //Load Conversations
            Call<ArrayList<Messages>> callMessages = apiInterface.getMessages(token, userID);
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

    public Result<Feed> loadFeed(String token, int userID) {
        try {
            /* handle loading homeFeed */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);

            //Load Friends
            Call<Feed> callFeed = apiInterface.getFeed(token, userID);
            Response<Feed> response = callFeed.execute();
            Feed rawFeed = (Feed) response.body();

            // TODO: Deserialize
            Gson gson = new Gson();
            String json = gson.toJson(rawFeed, Feed.class);
            Type feedType = new TypeToken<Feed>(){}.getType();
            Feed posts = gson.fromJson(json, feedType);
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


    public Result<NearbyUsers> loadNearby(String token) {
        try {
            /* handle loading nearby */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);

            // TODO: Finish AuPairNearby
            //Load nearby
            Call<NearbyUsers> callFeed = apiInterface.getNearby(token);
            Response<NearbyUsers> response = callFeed.execute();
            NearbyUsers newNearby = (NearbyUsers) response.body();

            Log.d(TAG, "loadNearby: Nearby loaded." + response.message());
            return new Result.Success<>(newNearby);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(new IOException("Error loading Nearby Users", e));
        }
    }

    // TODO: Not used currently
    public Result<User> loadUser() {
        return null;
    }

    public Result<Groups> loadGroups(String token, int userID) {
        try {
            /* handle loading groups */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);

            //Load Groups
            Call<Groups> callGroups = apiInterface.getGroups(token, userID);
            Response<Groups> response = callGroups.execute();
            Groups newGroups = null;
            if(response.isSuccessful()) {
                newGroups = (Groups) response.body();
                Log.d(TAG, "loadGroups: Groups loaded." + response.message());
            }
            return new Result.Success<>(newGroups);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new Result.Error(new NullPointerException(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(new IOException("Failed loading Groups.", e));
        }
    }

    public Result<Post> createPost(String tokenString, int userID, String post) {
        try {
            /* handle creating posts */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);
            DataRepository data = DataRepository.getInstance(new DataSource());
            Call<Post> sendCall = apiInterface.createPost(data.getTokenString(), data.getUser().getUserID(), post);
            Response<Post> response = sendCall.execute();
            Post pst = null;
            if (response.isSuccessful()) {
                pst = response.body();
                Log.d(TAG, "createPost: Post created." + response.message());
            }
            return new Result.Success<>(pst);
        } catch (NullPointerException e){
            e.printStackTrace();
            return new Result.Error(new NullPointerException(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(new IOException("Failed creating post."));
        }
    }
}
