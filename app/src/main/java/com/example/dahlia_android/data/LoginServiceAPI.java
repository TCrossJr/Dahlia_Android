package com.example.dahlia_android.data;

import com.example.dahlia_android.data.model.LoggedInUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/** TODO: Not currently used, plan to implement soon*/
public interface LoginServiceAPI {
    @POST("rest-auth/signin/")
    Call<LoggedInUser> getUserCredentials1(String authToken);

    // Unauthorized url-encoded
    @FormUrlEncoded
    @POST("/rest-auth/signin/")
    Call<LoggedInUser> getUserCredentials2(
            @Header("Authorization") String token,
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("rest-auth/signin/")
    Call<LoggedInUser> getUserCredentials(@Header("Authorization") String authHeader);
    //    Call<LoggedInUser> getUserCredentials(@Field("email") String email, @Field("password") String password );

    @POST("rest-auth/signin/")
    Call<LoggedInUser> getUserCredentials32();
    //    Call<LoggedInUser> getUserCredentials(@Field("email") String email, @Field("password") String password );

    @POST("rest-auth/signin/")
    Call<LoggedInUser> getUserCredentials4(@Field("email") String email, @Field("password") String password );
}
