package com.example.dahlia_android.ui.home;

import com.example.dahlia_android.ui.groups.Group;
import com.example.dahlia_android.ui.user.User;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("id") private int postID;
    @SerializedName("date_created") private String postDate; // TODO: Change to Date???
    @SerializedName("post_creator") private User postCreator; // TODO: RMV???
    private String profileImageURL; //TODO: Change to Image or ImageView???
    @SerializedName("post_text") private String postText;
    @SerializedName("post_liked") private Group postLikedBy;
    @SerializedName("post_media") private String mediaURL; //TODO: Change to Image or ImageView???
    @SerializedName("post_replies") private Feed replies;
    private int likes;

    public Post(int postID, String postDate, User postCreator, String profileImageURL, String postText,
                String mediaURL, Group group, Feed replies, int likes) {
        this.postID = postID;
        this.postDate = postDate;
        this.postCreator = postCreator;
        this.profileImageURL = profileImageURL;
        this.postText = postText;
        this.mediaURL = mediaURL;
        this.postLikedBy = group;
        this.replies = replies;
        this.likes = likes;
    }

    // TODO: RMV or Change
    public Post(String text, String date) {
        this.postID = -1;
        this.postDate = date;
        this.profileImageURL = "";
        this.postText = text;
        this.mediaURL = "";
        this.postLikedBy = null;
        this.replies = null;
        this.likes = -1;
    }

    public int getPostID() {
        return postID;
    }

    public User getPostCreator() {
        return postCreator;
    }

    public void setPostCreator(User postCreator) {
        this.postCreator = postCreator;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getMediaURL() {
        return mediaURL;
    }

    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }

    public Group getPostLikedBy() {
        return postLikedBy;
    }

    public void setPostLikedBy(Group postLikedBy) {
        this.postLikedBy = postLikedBy;
    }

    public Feed getReplies() {
        return replies;
    }

    public void setReplies(Feed replies) {
        this.replies = replies;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
