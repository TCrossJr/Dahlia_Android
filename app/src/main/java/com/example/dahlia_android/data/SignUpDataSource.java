package com.example.dahlia_android.data;

import android.util.Log;

import com.example.dahlia_android.api.APIClient;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.data.model.SignedUpUser;
import com.example.dahlia_android.data.model.UserToken;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class SignUpDataSource {

    private static final String TAG = "SignUpDataSource";
    private APIServiceInterface apiInterface;

    public SignUpResult<SignedUpUser> signup(String username, String userEmail, String password1, String password2, String firstName, String lastName, String agency) {

        try {
            /* handle signUpUser authentication */
            // get_token
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);
            Call<UserToken> tokenCall = apiInterface.getInitialToken();
            Response<UserToken> responseToken = tokenCall.execute();
            UserToken userToken = responseToken.body();
            Log.d(TAG, "signup: " + userToken.toString() );

            // post user signup
            Call<SignedUpUser> signUpUser = apiInterface.signUserUp(
                    userToken.getToken(), username, userEmail,
                    password1, password2, firstName, lastName, agency);
            Response<SignedUpUser> responseUser= signUpUser.execute();
            SignedUpUser newUser = responseUser.body();
            Log.d(TAG, "signup: " + newUser.toString() );
            return new SignUpResult.Success<>(newUser);
        } catch (Exception e) {
            return new SignUpResult.Error(new IOException("Error Signing in", e));
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
}