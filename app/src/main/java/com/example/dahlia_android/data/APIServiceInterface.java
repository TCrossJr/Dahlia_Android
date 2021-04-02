package com.example.dahlia_android.data;

import com.example.dahlia_android.data.model.LoggedInUser;
import com.example.dahlia_android.data.model.SignedUpUser;
import com.example.dahlia_android.data.model.UserToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/** TODO: Not currently used, plan to implement soon*/
public interface APIServiceInterface {
    // WORKS
    @GET("csrf/")
    Call<UserToken> getIntitalToken();

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

    @POST("rest-auth/signin/")
    Call<LoggedInUser> getUserCredentials0(@Header("Authorization") String authHeader);

    @POST("rest-auth/signin/")
    Call<LoggedInUser> getUserCredentials1(String authToken);
    // Unauthorized url-encoded

    @FormUrlEncoded
    @POST("/rest-auth/signin/")
    Call<LoggedInUser> getUserCredentials(
            @Header("Authorization") String token,
            @Field("email") String email,
            @Field("password") String password
    );
    //    Call<LoggedInUser> getUserCredentials(@Field("email") String email, @Field("password") String password );
}
