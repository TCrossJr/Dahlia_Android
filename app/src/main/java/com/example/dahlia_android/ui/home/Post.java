package com.example.dahlia_android.ui.home;

import com.example.dahlia_android.ui.friends.Friend;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private String profileImageURL; //TODO: Change to Image or ImageView???
    private String postDate; // TODO: Change to Date
    private String postText;
    private String mediaURL; //TODO: Change to Image or ImageView???
    private List<Friend> postLikedBy; //TODO: Change to List<User>???
    private int replies;
    private int likes;

    public Post(String profileImageURL, String postDate, String postText,
                String mediaURL, int replies, int likes) {
        this.profileImageURL = profileImageURL;
        this.postDate = postDate;
        this.postText = postText;
        this.mediaURL = mediaURL;
        this.postLikedBy = new ArrayList<>();
        this.replies = replies;
        this.likes = likes;
    }

    public Post() {
        this.profileImageURL = "";
        this.postDate = "";
        this.postText = "Test";
        this.mediaURL = "";
        this.postLikedBy = new ArrayList<>();
        this.replies = 0;
        this.likes = 0;
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

    public List<Friend> getPostLikedBy() {
        return postLikedBy;
    }

    public void setPostLikedBy(List<Friend> postLikedBy) {
        this.postLikedBy = postLikedBy;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
