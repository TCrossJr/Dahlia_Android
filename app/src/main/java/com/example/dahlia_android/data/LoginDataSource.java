package com.example.dahlia_android.data;

import android.util.Log;

import com.example.dahlia_android.ApplicationUser;
import com.example.dahlia_android.api.APIClient;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.data.model.LoggedInUser;
import com.example.dahlia_android.ui.user.User;

import java.io.IOException;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private static final String TAG = "LoginDataSource";
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

            //Load User // // TODO: change to ViewModel, DataSource, and DataRepository
            Call<User> load = apiInterface.getUser(token,user.getUserId());
            Response<User> response = load.execute();
            User loadedUser = response.body();
            Log.d(TAG, "loadUser: User loaded." + response.body());
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
}