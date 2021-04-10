package com.example.dahlia_android.data;

import com.example.dahlia_android.api.APIClient;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.data.model.SignedUpUser;
import com.example.dahlia_android.data.model.UserToken;

import java.io.IOException;

import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class SignUpDataSource {

    private APIServiceInterface apiInterface;

    public SignUpResult<SignedUpUser> signup(String username, String userEmail, String password1, String password2, String firstName, String lastName, String agency) {

        try {
            // handle signUpUser authentication

            // get_token
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);
            Response<UserToken> tokenResponse = apiInterface.getInitialToken().execute();
            UserToken userToken = tokenResponse.body();

            // post user signup
            // TODO: Maybe add post to a Call instead of a Response to do Async not on main thread
            Response<SignedUpUser> signUpUser = apiInterface.signUserUp(
                    userToken.getToken(), username, userEmail,
                    password1, password2, firstName, lastName, agency).execute();
            SignedUpUser newUser = signUpUser.body();

            return new SignUpResult.Success<>(newUser);
        } catch (Exception e) {
            return new SignUpResult.Error(new IOException("Error Signing in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}