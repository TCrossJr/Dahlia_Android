package com.example.dahlia_android.data;

import com.example.dahlia_android.data.model.SignedUpUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class SignUpDataSource {

    // TODO: Change to handle other fields in Sign Up
    public SignUpResult<SignedUpUser> signup(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            SignedUpUser fakeUser =
                    new SignedUpUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new SignUpResult.Success<>(fakeUser);
        } catch (Exception e) {
            return new SignUpResult.Error(new IOException("Error Signing in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}