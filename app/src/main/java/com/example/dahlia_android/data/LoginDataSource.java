package com.example.dahlia_android.data;

import android.util.Log;

import com.example.dahlia_android.api.APIClient;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.data.model.LoggedInUser;

import java.io.IOException;

import okhttp3.Credentials;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private static final String TAG = "LoginDataSource";
    private APIServiceInterface apiInterface;

    // TODO: The app is using cleartext enabled right now because https:// not implemented yet on server
    public Result<LoggedInUser> login(final String username, final String password) {

        try {
            /* handle loggedInUser authentication */
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);

            //@Post username and pw
            String credentials = Credentials.basic(username, password);
            Response<LoggedInUser> getUser = apiInterface.getUserCredentials(
                    credentials, username, password).execute();
            LoggedInUser user = getUser.body();

            return new Result.Success<>(user);
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
}