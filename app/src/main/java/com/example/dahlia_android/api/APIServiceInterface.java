package com.example.dahlia_android.api;

import com.example.dahlia_android.data.model.LoggedInUser;
import com.example.dahlia_android.data.model.SignedUpUser;
import com.example.dahlia_android.data.model.UserToken;
import com.example.dahlia_android.ui.user.UserProfile;

import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIServiceInterface {
    // WORKS
    @GET("csrf/")
    Call<UserToken> getInitialToken();

    // WORKS
    @FormUrlEncoded
    @POST("rest-auth/signup/")
    Call<SignedUpUser> signUserUp(@Header("Authorization") String token,
                                  @Field("username") String userName,
                                  @Field("email") String email,
                                  @Field("password1") String password1,
                                  @Field("password2") String password2,
                                  @Field("first_name") String firstName,
                                  @Field("last_name") String lastName,
                                  @Field("agency") String agency );

    // WORKS
    @FormUrlEncoded
    @POST("/rest-auth/signin/")
    Call<LoggedInUser> getUserCredentials(
            @Header("Authorization") String token,
            @Field("email") String email,
            @Field("password") String password
    );

    //
    @FormUrlEncoded
    @GET("/rest-auth/create_profile/")
    Response<UserProfile> createProfile2(
            @Header("Authorization") String token
    );

    //
    @FormUrlEncoded
    @GET("/rest-auth/create_profile/")
    Call<UserProfile> createProfile(
            @Header("Authorization") String token,
            @Field("date_of_birth") String dateOfBirth,
            @Field("town") String town,
            @Field("state") String state,
            @Field("zipcode") String zipCode,
            @Field("description") String description
    );

    // TODO: Finish logout, need to send token on postman, might already work on android
    @GET("/rest-auth/signout/")
    Call<String> logout(@Header("Authorization") String token);
}
