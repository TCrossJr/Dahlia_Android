package com.example.dahlia_android.data;

import com.example.dahlia_android.data.model.SignedUpUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class SignUpRepository {

    private static volatile SignUpRepository instance;

    private SignUpDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private SignedUpUser user = null;

    // private constructor : singleton access
    private SignUpRepository(SignUpDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static SignUpRepository getInstance(SignUpDataSource dataSource) {
        if (instance == null) {
            instance = new SignUpRepository(dataSource);
        }
        return instance;
    }

    public boolean isSignedUp() {
        return user != null;
    }

    // TODO: RMV/CHANGE???
    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setSignedUpUser(SignedUpUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public SignUpResult<SignedUpUser> signup(String username, String password) {
        // handle login
        SignUpResult<SignedUpUser> result = dataSource.signup(username, password);
        if (result instanceof SignUpResult.Success) {
            setSignedUpUser(((SignUpResult.Success<SignedUpUser>) result).getData());
        }
        return result;
    }
}