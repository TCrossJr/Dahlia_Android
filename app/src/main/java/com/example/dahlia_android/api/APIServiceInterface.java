package com.example.dahlia_android.api;

import com.example.dahlia_android.data.model.LoggedInUser;
import com.example.dahlia_android.data.model.SignedUpUser;
import com.example.dahlia_android.data.model.UserToken;
import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.groups.Group;
import com.example.dahlia_android.ui.groups.Groups;
import com.example.dahlia_android.ui.home.Feed;
import com.example.dahlia_android.ui.home.Post;
import com.example.dahlia_android.ui.messages.Message;
import com.example.dahlia_android.ui.messages.Messages;
import com.example.dahlia_android.ui.nearby.AuPairNearby;
import com.example.dahlia_android.ui.nearby.NearbyUsers;
import com.example.dahlia_android.ui.user.User;
import com.example.dahlia_android.ui.user.UserProfile;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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


    //WORKS but removeFriends2 is preferred method using session authentication and retrieving userID
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


    //WORKS
    @FormUrlEncoded
    @POST("/messages/send_message/")
    Call<Message> sendMessage(
            @Header("Authorization") String token,
            @Field("message_creator") int creatorID,
            @Field("message_receiver") int receiverID,
            @Field("message_text") String messageText);

    //WORKS
    @GET("/messages/get_messages/{userID}")
    Call<ArrayList<Messages>> getMessages(
            @Header("Authorization") String token,
            @Path("userID") int userID);

    //
    @POST("/messages/remove_message/")
    Call<Void> removeMessage(
            @Header("Authorization") String token,
            @Field("messageID") int messageID);


    //WORKS
    @POST("/messages/remove_messages/")
    Call<Void> removeMessages(
            @Header("Authorization") String token,
            @Field("userID") int userID,
            @Field("friendID") int friendID);


    /** Posts */
    //WORKS
    @GET("/posts/feed/{userID}")
    Call<Feed> getFeed(
            @Header("Authorization") String token,
            @Path("userID") int userID);

    //WORKS
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

    //WORKS
    @DELETE("/posts/remove_post/{postID}")
    Call<Void> removePost(
            @Header("Authorization") String token,
            @Path("postID") int postID);


    /** Groups */
    //WORKS
    @FormUrlEncoded
    @POST("/groups/create_group/")
    Call<Group> createGroup(
            @Header("Authorization") String token,
            @Field("group_creator") int creator,
            @Field("group_name") String groupName,
            @Field("group_users") int users);

    //WIP
    @FormUrlEncoded
    @POST("/groups/create_group/")
    Call<Group> createGroup0(
            @Header("Authorization") String token,
            @Field("group_creator") User creator,
            @Field("group_name") String groupName,
            @Field("group_users") User users);

    //WORKS
    @GET("/groups/get_groups/{userID}")
    Call<Groups> getGroups(
            @Header("Authorization") String token,
            @Path("userID") int userID);

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


    /** AuPair Nearby */
    //
    @GET("/aupairnearby/get_nearby/")
    Call<NearbyUsers> getNearby(
            @Header("Authorization") String token);

    //
    @FormUrlEncoded
    @POST("/aupairnearby/create_nearby/")
    Call<AuPairNearby> createNearby(
            @Header("Authorization") String token,
            @Field("user") int user,
            @Field("enable") boolean enable,
            @Field("latitude") Double latitude,
            @Field("longitude") Double longitude);

    //
    @DELETE("/aupairnearby/remove_nearby/{nearbyID}")
    Call<Void> removeNearby(
            @Header("Authorization") String token,
            @Path("nearbyID") int nearbyID);
}
