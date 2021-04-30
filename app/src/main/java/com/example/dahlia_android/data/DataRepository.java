package com.example.dahlia_android.data;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.messages.Conversations;
import com.example.dahlia_android.ui.messages.Messages;
import com.example.dahlia_android.ui.user.User;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of User, Feed, FriendsList, Groups, and Messages.
 */
public class DataRepository {

    private static volatile DataRepository instance;

    private DataSource dataSource;

    private User user = null; // TODO:// RMV??? Might use for loadUser(User currently stored on LoginDataSource and MainActivity/SharedPreferences)
    private FriendsList friends_list = null;
    private Conversations conversations = null;

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

    public Conversations getConversations() {
        return conversations;
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

    private void setMessages(Conversations data) { this.conversations = data; }

    public Result<Conversations> loadConversations() {
        // handle loading messages
        Result<Conversations> conversationsResult = dataSource.loadMessages();
        if (conversationsResult instanceof Result.Success) {
            setMessages(((Result.Success<Conversations>) conversationsResult).getData());
        }
        return conversationsResult;
    }

    public Messages getConversation(int position) {
        return (Messages) conversations.get(position);
    }
}