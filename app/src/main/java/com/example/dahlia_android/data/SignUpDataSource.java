package com.example.dahlia_android.data;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.util.Log;

import com.example.dahlia_android.MainActivity;
import com.example.dahlia_android.api.APIClient;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.data.model.SignedUpUser;
import com.example.dahlia_android.data.model.UserToken;
import com.example.dahlia_android.ui.user.UserProfile;

import java.io.CharArrayWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Enumeration;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
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
            Response<UserToken> tokenResponse = apiInterface.getInitialToken().execute();
            UserToken userToken = tokenResponse.body();
            Log.d(TAG, "signup: " + userToken.toString() );

            // post user signup
            Response<SignedUpUser> signUpUser = apiInterface.signUserUp(
                    userToken.getToken(), username, userEmail,
                    password1, password2, firstName, lastName, agency).execute();
            SignedUpUser newUser = signUpUser.body();
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