package com.example.dahlia_android.ui.home;

import com.example.dahlia_android.ui.groups.Group;
import com.example.dahlia_android.ui.user.User;

public class Post {

    private int postID;
    private User postCreator; // TODO: RMV???
    private String profileImageURL; //TODO: Change to Image or ImageView???
    private String postDate; // TODO: Change to Date???
    private String postText;
    private String mediaURL; //TODO: Change to Image or ImageView???
    private Group postLikedBy;
    private Feed replies;
    private int likes;

    public Post(int postID, User postCreator, String profileImageURL, String postDate, String postText,
                String mediaURL, Group group, Feed replies, int likes) {
        this.postID = postID;
        this.postCreator = postCreator;
        this.profileImageURL = profileImageURL;
        this.postDate = postDate;
        this.postText = postText;
        this.mediaURL = mediaURL;
        this.postLikedBy = group;
        this.replies = replies;
        this.likes = likes;
    }

    // TODO: RMV or Change
    public Post(String text, String date) {
        this.profileImageURL = "";
        this.postDate = date;
        this.postText = text;
        this.mediaURL = "";
        this.postLikedBy = null;
        this.replies = null;
        this.likes = 0;
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
