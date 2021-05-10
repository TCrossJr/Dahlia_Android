package com.example.dahlia_android.data;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.groups.Group;
import com.example.dahlia_android.ui.groups.Groups;
import com.example.dahlia_android.ui.home.Feed;
import com.example.dahlia_android.ui.messages.Conversations;
import com.example.dahlia_android.ui.messages.Messages;
import com.example.dahlia_android.ui.nearby.NearbyUsers;
import com.example.dahlia_android.ui.user.User;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of User, Feed, FriendsList, Groups, and Messages.
 */
public class DataRepository {

    private static volatile DataRepository instance;

    private DataSource dataSource;

    private User user = null; // TODO:// RMV??? Might use for loadUser
    private FriendsList friends_list = null;
    private Conversations conversations = null;
    private Feed feed = null;
    private NearbyUsers nearbyUsers = null;
    private Groups groups = null;

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

    public void logout() {
        user = null;
        dataSource.logout();
    }

    /** User/Login */
    public User getFriendByID(int userID) {
        User user;
        for( Object usr : friends_list ) {
            user = (User) usr;
            if( user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    public User getUser() {
        return user;
    }

    private void setUser(User data) {
        this.user = data;
    }

    private void setLoggedInUser(User user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<User> login(String username, String password) {
        // handle login
        Result<User> loginResult = dataSource.login(username, password);
        if (loginResult instanceof Result.Success) {
            setLoggedInUser(((Result.Success<User>) loginResult).getData());
        }
        return loginResult;
    }

    public Result<User> loadUser() {
        // handle loading user
        Result<User> userResult = dataSource.loadUser();
        if (userResult instanceof Result.Success) {
            setUser(((Result.Success<User>) userResult).getData());
        }
        return userResult;
    }

    /** Feed */
    public Feed getFeed() {
        return feed;
    }

    private void setFeed(Feed data) {
        this.feed = data;
    }

    public Result<Feed> loadFeed() {
        // handle loading feed
        Result<Feed> feedResult = dataSource.loadFeed();
        if (feedResult instanceof Result.Success) {
            setFeed(((Result.Success<Feed>) feedResult).getData());
        }
        return feedResult;
    }

    /** FriendsList */
    public FriendsList getFriends() {
        return friends_list;
    }

    private void setFriends(FriendsList data) {
        this.friends_list = data;
    }

    public Result<FriendsList> loadFriends() {
        // handle loading friends
        Result<FriendsList> friendsListResult = dataSource.loadFriends();
        if (friendsListResult instanceof Result.Success) {
            setFriends(((Result.Success<FriendsList>) friendsListResult).getData());
        }
        return friendsListResult;
    }

    /** Messages */
    public Conversations getMessages() {
        return conversations;
    }

    private void setMessages(Conversations data) { this.conversations = data; }

    public Messages getConversation(int position) {
        return (Messages) conversations.get(position);
    }

    public Result<Conversations> loadConversations() {
        // handle loading messages
        Result<Conversations> conversationsResult = dataSource.loadMessages();
        if (conversationsResult instanceof Result.Success) {
            setMessages(((Result.Success<Conversations>) conversationsResult).getData());
        }
        return conversationsResult;
    }

    /** AuPair Nearby */
    public Result<NearbyUsers> loadNearby() {
        // handle loading nearby
        Result<NearbyUsers> nearbyUsersResult = dataSource.loadNearby();
        if (nearbyUsersResult instanceof Result.Success) {
            setNearby(((Result.Success<NearbyUsers>) nearbyUsersResult).getData());
        }
        return nearbyUsersResult;
    }

    private void setNearby(NearbyUsers data) {
        this.nearbyUsers = data;
    }

    public NearbyUsers getNearbyUsers() {
        return (NearbyUsers) nearbyUsers;
    }

    public Result<Groups> loadGroups() {
        // handle loading groups
        Result<Groups> groupsResult = dataSource.loadGroups();
        if (groupsResult instanceof Result.Success) {
            setGroups(((Result.Success<Groups>) groupsResult).getData());
        }
        return groupsResult;
    }

    private void setGroups(Groups data) {
        this.groups = data;
    }

    public Groups getGroups() {
        return (Groups)groups;
    }
}