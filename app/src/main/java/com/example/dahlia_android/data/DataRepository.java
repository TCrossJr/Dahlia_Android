package com.example.dahlia_android.data;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.user.User;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of User, Feed, FriendsList, Groups, and Messages.
 */
public class DataRepository {

    private static volatile DataRepository instance;

    private DataSource dataSource;

    private User user = null; // TODO:// RMV???
    private FriendsList friends_list = null;

    // private constructor : singleton access
    private DataRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static DataRepository getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new DataRepository(dataSource);
        }
        return instance;
    }

    public boolean isFriendsList() {
        return friends_list != null;
    }

/*    public void logout() {
        user = null;
        dataSource.logout();
    }*/

    public FriendsList getFriendsList() {
        return friends_list;
    }

    private void setFriends(FriendsList friends) {
        this.friends_list = friends;
    }

    public Result<FriendsList> loadFriends() {
        // handle loading friends
        Result<FriendsList> friendsListResult = dataSource.loadFriends();
        if (friendsListResult instanceof Result.Success) {
            setFriends(((Result.Success<FriendsList>) friendsListResult).getData());
        }
        return friendsListResult;
    }
}