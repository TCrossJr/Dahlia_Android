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
            //@Post username, email, pw1, pw2, first, last, agency, age

            //get response
/*-------------------------------------------------------------------*/
            // WORKS!!!!! User Created Editing Postman, okhttp, retrofit Combined

//            // get_token
            apiInterface = APIClient.getClient().create(APIServiceInterface.class);
//            Call<UserToken> call = apiInterface.getIntitalToken();
            Response<UserToken> tokenResponse = apiInterface.getIntitalToken().execute();
            UserToken userToken = tokenResponse.body();
//            token = userToken.getToken();

            // TODO: Maybe add post to a Call instead of a Response to do Async not on main thread
            // post user signup
            Response<SignedUpUser> signUpUser = apiInterface.signUserUp(
                    userToken.getToken(), username, userEmail,
                    password1, password2, firstName, lastName, agency).execute();
/*
            Call<SignedUpUser> userCall = apiInterface.signUserUp(
                    token, username, userEmail,
                    password1, password2, firstName, lastName, agency);
            userCall.enqueue(new Callback<SignedUpUser>() {
                @Override
                public void onResponse(Call<SignedUpUser> call, Response<SignedUpUser> response) {
                    Log.d("SIGNED_UP_USER", response.code() + " ");
                    SignedUpUser newUser = response.body();
                    String str = "";
                }

                @Override
                public void onFailure(Call<SignedUpUser> call, Throwable t) {
                    userCall.cancel();
                }
            });
*/
/*
//            // try converting to json
//            SignedUpUser jsonUser =
//                    new SignedUpUser(
//                            "TestJsonUser",
//                            "T29@test.com",
//                            "Test",
//                            "Json",
//                            "Au Pair");
//            String userBody = gson.toJson(jsonUser);
//*/
/*
            // build signup
            MediaType mediaType = MediaType.parse("text/plain");
//            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("username","TestUser")
                    .addFormDataPart("email","t23@test.com")
                    .addFormDataPart("password1","testUserpw1")
                    .addFormDataPart("password2","testUserpw1")
                    .addFormDataPart("first_name","Tim")
                    .addFormDataPart("last_name","Cross")
                    .addFormDataPart("agency","Au Pair")
                    .build();
//            RequestBody requestBody = RequestBody.create(userBody,mediaType);
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(newClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();

//            retrofit.create()

            Request requestSignUp = new Request.Builder()
                    .url("http://10.0.2.2:8000/rest-auth/signup/")
                    .addHeader("X-XSRF-TOKEN", str )
                    .post(body)
                    .build();

//                    .post(RequestBody.create(userBody,MediaType.parse("application/json"))
*//*
            Request requestSignUp = new Request.Builder()
                    .url("http://10.0.2.2:8000/rest-auth/signup/")
                    .method("POST", body)
                    .header("X-XSRF-TOKEN", str )
                    .build();
*//*

            // handle signup response
//            Response response = newClient.newCall(request).execute();
            ResponseBody responseBodySignUp = newClient.newCall(requestSignUp).execute().body();
            SignedUpUser user = gson.fromJson(responseBodySignUp.string(), SignedUpUser.class);
            String tmp = "";*/
/*-------------------------------------------------------------------*/
/*
            // Editing Postman, okhttp, retrofit Combined
            OkHttpClient.Builder client = new OkHttpClient().newBuilder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(); //TODO: can remove this interceptor when done testing
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(loggingInterceptor);
            OkHttpClient newClient = client.build();

            // get_token
            Request requestToken = new Request.Builder()
                    .url("http://10.0.2.2:8000/csrf/")
                    .method("GET", null)
                    .build();
//            Response tokenResponse = newClient.newCall(requestToken).execute();

            // retrofit
            TokenInterceptor tokenIntercepter = new TokenInterceptor(token);
            setupRetrofit(tokenIntercepter, BASE_URL);
            client.addInterceptor(loggingInterceptor)
                    .addInterceptor(tokenIntercepter);
            OkHttpClient newClient = client.build();



            //handle token response
            Gson gson = new Gson();
            ResponseBody responseBodyToken = newClient.newCall(requestToken).execute().body();
            UserToken token = gson.fromJson(responseBodyToken.string(), UserToken.class);
//            TokenInterceptor token = gson.fromJson(responseBodyToken.string(), TokenInterceptor.class);
//            String str = "csrftoken="+ token.getToken();
            String str = "Token "+ token.getToken();
            String tmp2 = ""; // TODO: RMV
            Log.d("TOKEN-RESPONSE", "signup: "+ str);

            // try converting to json
            SignedUpUser jsonUser =
                    new SignedUpUser(
                            "TestJsonUser",
                            "T29@test.com",
                            "Test",
                            "Json",
                            "Au Pair");
            String userBody = gson.toJson(jsonUser);
*//*


            // build signup
            MediaType mediaType = MediaType.parse("text/plain");
//            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("username","TestUser")
                    .addFormDataPart("email","t23@test.com")
                    .addFormDataPart("password1","testUserpw1")
                    .addFormDataPart("password2","testUserpw1")
                    .addFormDataPart("first_name","Tim")
                    .addFormDataPart("last_name","Cross")
                    .addFormDataPart("agency","Au Pair")
                    .build();
//            RequestBody requestBody = RequestBody.create(userBody,mediaType);
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(newClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();

//            retrofit.create()

            Request requestSignUp = new Request.Builder()
                    .url("http://10.0.2.2:8000/rest-auth/signup/")
                    .addHeader("X-XSRF-TOKEN", str )
                    .post(body)
                    .build();

//                    .post(RequestBody.create(userBody,MediaType.parse("application/json"))
*/
/*
            Request requestSignUp = new Request.Builder()
                    .url("http://10.0.2.2:8000/rest-auth/signup/")
                    .method("POST", body)
                    .header("X-XSRF-TOKEN", str )
                    .build();
*//*


            // handle signup response
//            Response response = newClient.newCall(request).execute();
            ResponseBody responseBodySignUp = newClient.newCall(requestSignUp).execute().body();
            SignedUpUser user = gson.fromJson(responseBodySignUp.string(), SignedUpUser.class);
            String tmp = "";
*/
/*-------------------------------------------------------------------*/
            // Not Working Postman Combined WIP
/*
            OkHttpClient.Builder client = new OkHttpClient().newBuilder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(); //TODO: can remove this interceptor when done testing
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(loggingInterceptor);
            OkHttpClient newClient = client.build();

            // get_token
            Request requestToken = new Request.Builder()
                    .url("http://10.0.2.2:8000/csrf/")
                    .method("GET", null)
                    .build();
//            Response tokenResponse = newClient.newCall(requestToken).execute();

            //handle token response
            Gson gson = new Gson();
            ResponseBody responseBodyToken = newClient.newCall(requestToken).execute().body();
            UserToken token = gson.fromJson(responseBodyToken.string(), UserToken.class);
//            String str = "csrftoken="+ token.getToken();
            String str = "Token "+ token.getToken();
            String tmp2 = "";
            Log.d("TOKEN-RESPONSE", "signup: "+ str);

            // try converting to json
            SignedUpUser jsonUser =
                    new SignedUpUser(
                            "TestJsonUser",
                            "T29@test.com",
                            "Test",
                            "Json",
                            "Au Pair");
            String userBody = gson.toJson(jsonUser);

            // build signup
            MediaType mediaType = MediaType.parse("text/plain");
//            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("username","TestUser")
                    .addFormDataPart("email","t23@test.com")
                    .addFormDataPart("password1","testUserpw1")
                    .addFormDataPart("password2","testUserpw1")
                    .addFormDataPart("first_name","Tim")
                    .addFormDataPart("last_name","Cross")
                    .addFormDataPart("agency","Au Pair")
                    .build();
//            RequestBody requestBody = RequestBody.create(userBody,mediaType);
            Request requestSignUp = new Request.Builder()
                    .url("http://10.0.2.2:8000/rest-auth/signup/")
                    .addHeader("X-XSRF-TOKEN", str )
                    .addHeader("Accept-Encoding", "gzip, deflate, br")
                    .post(body)
                    .build();

//                    .post(RequestBody.create(userBody,MediaType.parse("application/json"))
*/
/*
            Request requestSignUp = new Request.Builder()
                    .url("http://10.0.2.2:8000/rest-auth/signup/")
                    .method("POST", body)
                    .header("X-XSRF-TOKEN", str )
                    .build();
*//*


            // handle signup response
//            Response response = newClient.newCall(request).execute();
            ResponseBody responseBodySignUp = newClient.newCall(requestSignUp).execute().body();
            SignedUpUser user = gson.fromJson(responseBodySignUp.string(), SignedUpUser.class);
            String tmp = "";
*/

/*-------------------------------------------------------------------*/
            // WORKS!!!!!!! Postman
/*
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("username","TestUser")
                    .addFormDataPart("email","t23@test.com")
                    .addFormDataPart("password1","testUserpw1")
                    .addFormDataPart("password2","testUserpw1")
                    .addFormDataPart("first_name","Tim")
                    .addFormDataPart("last_name","Cross")
                    .addFormDataPart("agency","Au Pair")
                    .build();
            Request request = new Request.Builder()
                    .url("http://localhost:8000/rest-auth/signup/")
                    .method("POST", body)
                    .addHeader("X-XSRF-TOKEN", "Token 8i3Mfu78wAvxYOLyVGdFegBHw5eRuIXR83kPzSHKoJ6Jy4yitFgLAL65c8ZeHDGi")
                    .build();
            Response response = client.newCall(request).execute();
*/
/*-------------------------------------------------------------------*/
            // Combined Get and Post
/*
            OkHttpClient.Builder client = new OkHttpClient().newBuilder();

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(); //TODO: can remove this interceptor when done testing
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(loggingInterceptor);
            OkHttpClient newClient = client.build();

            // get_token
            Request requestToken = new Request.Builder()
                    .url("http://10.0.2.2:8000/csrf/")
                    .method("GET", null)
                    .build();
//            Response tokenResponse = newClient.newCall(requestToken).execute();

            //handle token response
            Gson gson = new Gson();
            ResponseBody responseBodyToken = newClient.newCall(requestToken).execute().body();
            UserToken token = gson.fromJson(responseBodyToken.string(), UserToken.class);
//            String str = "csrftoken="+ token.getToken();
            String str = "Token "+ token.getToken();
            String tmp2 = "";
            Log.d("TOKEN-RESPONSE", "signup: "+ str);

            // build signup
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("username","NewUser16")
                    .addFormDataPart("email","t16@test.com")
                    .addFormDataPart("password1","testUserpw1")
                    .addFormDataPart("password2","testUserpw1")
                    .addFormDataPart("first_name","Tim")
                    .addFormDataPart("last_name","Cross")
                    .addFormDataPart("agency","Au Pair")
                    .build();
            Request requestSignUp = new Request.Builder()
                    .url("http://10.0.2.2:8000/rest-auth/signup/")
                    .method("POST", body)
                    .addHeader("Authorization", str)
                    .build();

            // handle signup response
//            Response response = newClient.newCall(request).execute();
            ResponseBody responseBodySignUp = newClient.newCall(requestSignUp).execute().body();
            SignedUpUser user = gson.fromJson(responseBodySignUp.string(), SignedUpUser.class);
            String tmp = "";
*/
/*-------------------------------------------------------------------*/
            // Postman GET new token and POST EDITED
/*
            OkHttpClient.Builder client = new OkHttpClient().newBuilder();

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(); //TODO: can remove this interceptor when done testing
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(loggingInterceptor);
            OkHttpClient newClient = client.build();

            Request request = new Request.Builder()
                    .url("http://10.0.2.2:8000/csrf/")
                    .method("GET", null)
                    .build();

            Gson gson = new Gson();
            ResponseBody responseBody = newClient.newCall(request).execute().body();
            UserToken token = gson.fromJson(responseBody.string(), UserToken.class);
            String str = token.getToken();
            Log.d("TOKEN-RESPONSE", "signup: "+ str);

            // POST Sign Up new User
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "username=NewUser9&email=t9@test.com&password1=testUserpw1&password2=testUserpw1&first_name=Tim&last_name=Cross&agency=Au Pair");
            Request requestSignUp
                    = new Request.Builder()
                    .url("http://10.0.2.2:8000/rest-auth/signup/")
                    .method("POST", body )
                    .addHeader("Cookie", "csrftoken="+str )
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
//                    .addHeader("Cookie", "{{xsrf-token}}; csrftoken=bMbfPaSHBlDxuUxYZvCgW8k1Ds6W8pQAxnYuLtWud7fxsgpVAmdVI1JXtTfffcCx")
            Response response = newClient.newCall(requestSignUp).execute();//            Response response = newClient.newCall(request).execute();
            Log.d("SIGNUP-RESPONSE", "signup: "+ str);
*/
/*-------------------------------------------------------------------*/
            // Postman GET new token
/*
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("http://localhost:8000/csrf/")
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
*/
/*-------------------------------------------------------------------*/
            // First Postman version
/*
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("http://localhost:8000/csrf/")
                    .method("GET", null)
                    .addHeader("Cookie", "csrftoken=8i3Mfu78wAvxYOLyVGdFegBHw5eRuIXR83kPzSHKoJ6Jy4yitFgLAL65c8ZeHDGi")
                    .build();
            Response response = client.newCall(request).execute();
*/
            SignedUpUser fakeUser =
                    new SignedUpUser(
                            "TestUserName",
                            "T28@test.com",
                            "Test",
                            "Lastname",
                            "Au Pair");
            return new SignUpResult.Success<>(fakeUser);
        } catch (Exception e) {
            return new SignUpResult.Error(new IOException("Error Signing in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}