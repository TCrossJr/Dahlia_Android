package com.example.dahlia_android.api;

import com.example.dahlia_android.data.model.LoggedInUser;
import com.example.dahlia_android.data.model.SignedUpUser;
import com.example.dahlia_android.data.model.UserToken;
import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.home.Feed;
import com.example.dahlia_android.ui.home.Post;
import com.example.dahlia_android.ui.home.PostSend;
import com.example.dahlia_android.ui.messages.Conversations;
import com.example.dahlia_android.ui.messages.Message;
import com.example.dahlia_android.ui.messages.Messages;
import com.example.dahlia_android.ui.messages.RawMessage;
import com.example.dahlia_android.ui.user.User;
import com.example.dahlia_android.ui.user.UserProfile;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIServiceInterface {

    /** Token */
    @GET("csrf/")
    Call<UserToken> getInitialToken();


    /** SignUp, SignIn, and SignOut */
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

    // TODO: Finish logout, need to add token. works on postman, might already work on android
    @GET("/rest-auth/signout/")
    Call<String> logout(@Header("Authorization") String token);


    /** User */
    //WORKS
    @GET("/rest-auth/get_user/{userID}")
    Call<User> getUser(
            @Header("Authorization") String token,
            @Path("userID") int userID );

    //WIP
    @FormUrlEncoded
    @GET("/rest-auth/create_profile/")
    Response<UserProfile> createProfile2(
            @Header("Authorization") String token);

    //WIP
    @FormUrlEncoded
    @POST("/rest-auth/create_profile/")
    Call<UserProfile> createProfile(
            @Header("Authorization") String token,
            @Field("date_of_birth") String dateOfBirth,
            @Field("town") String town,
            @Field("state") String state,
            @Field("zipcode") String zipCode,
            @Field("description") String description
    );


    /** Friends */
    //WORKS
    @GET("/friends/friends_list/{userID}")
    Call<FriendsList> getFriends(
            @Header("Authorization") String token,
            @Path("userID") int userID );

    //WIP
    @POST("/friends/remove_friend/{friendID}")
    Call<Void> removeFriend2(
            @Header("Authorization") String token,
            @Path("friendID") int friendID);


    // Works but removeFriends2 is preferred method using session authentication and retrieving userID
    @DELETE("/friends/remove_friend/{friendID}/{userID}")
    Call<Void> removeFriend(
            @Header("Authorization") String token,
            @Path("friendID") int friendID,
            @Path("userID") int userID);


    /** Messages */
    // Actual way we need to do it
    @FormUrlEncoded
    @POST("/messages/send_message/")
    Call<Message> sendMessage0(
            @Header("Authorization") String token,
            @Field("message_creator") int creatorID,
            @Field("message_receiver") int receiverID,
            @Field("message_text") String messageText,
            @Field("message_media") String messageMedia);


    //Works
    @FormUrlEncoded
    @POST("/messages/send_message/")
    Call<Message> sendMessage(
            @Header("Authorization") String token,
            @Field("message_creator") int creatorID,
            @Field("message_receiver") int receiverID,
            @Field("message_text") String messageText);

    // Works
    @GET("/messages/get_messages/{userID}")
    Call<ArrayList<Messages>> getMessages(
            @Header("Authorization") String token,
            @Path("userID") int userID);

    //
    @POST("/messages/remove_message/")
    Call<Void> removeMessage(
            @Header("Authorization") String token,
            @Field("messageID") int messageID);


    //
    @POST("/messages/remove_messages/")
    Call<Void> removeMessages(
            @Header("Authorization") String token,
            @Field("userID") int userID,
            @Field("friendID") int friendID);


    /** ------------------------------------------------ */
    /** Posts */
    //Works
    @GET("/posts/feed/{userID}")
    Call<Feed> getFeed(
            @Header("Authorization") String token,
            @Path("userID") int userID);

    //Works
    @FormUrlEncoded
    @POST("/posts/create_post/")
    Call<Post> createPost(
            @Header("Authorization") String token,
            @Field("post_creator") int userID,
            @Field("post_text") String postText);

    //
    // TODO: removeFriends2 is preferred method using session authentication and retrieving userID
    @POST("/posts/remove_post/{postID}")
    Call<Void> removePost2(
            @Header("Authorization") String token,
            @Path("postID") int postID);

    //
    @DELETE("/posts/remove_post/{postID}")
    Call<Void> removePost(
            @Header("Authorization") String token,
            @Path("postID") int postID);


    /** Groups */
    // Remove User from Group
    @POST("/groups/remove_group_user/{groupID}/{userID}")
    Call<Void> removeGroupUser(
            @Header("Authorization") String token,
            @Path("groupID") int groupID,
            @Path("userID") int userID);

    // TODO: need to check if they're group creator
    @POST("/groups/remove_group/{groupID}")
    Call<Void> removeGroup(
            @Header("Authorization") String token,
            @Path("groupID") int groupID);
}
