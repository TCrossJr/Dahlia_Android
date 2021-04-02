package com.example.dahlia_android.data;

import android.util.Log;

import com.example.dahlia_android.data.model.SignedUpUser;
import com.example.dahlia_android.data.model.UserToken;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
            Response<UserToken> tokenResponse = apiInterface.getIntitalToken().execute();
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