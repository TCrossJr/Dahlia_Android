package com.example.dahlia_android.data;

import com.example.dahlia_android.data.model.LoggedInUser;
import com.google.gson.Gson;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(final String username, final String password) {

        try {
            // For testing TODO: RMV
            String finalUsername = "t05401btv@gmail.com";
            String finalPassword = "testUserpw1";

            // TODO: handle loggedInUser authentication
            //@Post username and pw

            //get response

            //make object, save to keystore

            /*-------------------------------------------------------------------*/
             // WORKS!!!! with HttpLoggingInterceptor TODO: Need to deal with hard-coded POST using LoginInterceptor
            OkHttpClient.Builder client = new OkHttpClient().newBuilder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(); //TODO: can remove this interceptor when done testing
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(loggingInterceptor);
            OkHttpClient newClient = client.build();

            // .url("http://127.0.0.1:8000/rest-auth/signin/")
            // .url("http://10.0.2.2:8000/rest-auth/signin/")
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create("email=t05401btv@gmail.com&password=testUserpw1",mediaType);
            Request request = new Request.Builder()
                    .url("http://10.0.2.2:8000/rest-auth/signin/")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic dDA1NDAxYnR2QGdtYWlsLmNvbTp0ZXN0VXNlcnB3MQ==")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build(); // TODO: The app is using cleartext enabled right now because https:// not implemented yet on server

            Gson gson = new Gson();
            ResponseBody responseBody = newClient.newCall(request).execute().body();
            LoggedInUser user = gson.fromJson(responseBody.string(), LoggedInUser.class);
            responseBody.close();
            /*-------------------------------------------------------------------*/
            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}