package com.example.dahlia_android.ui.home;

import com.example.dahlia_android.ui.groups.Group;
import com.example.dahlia_android.ui.user.User;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

public class Post {

    @SerializedName("id") private int postID;
    @SerializedName("date_created") private String postDate; // TODO: Change to Date???
    @SerializedName("post_creator_id") private int postCreator; // TODO: RMV???
    private String profileImageURL; //TODO: Change to Image or ImageView???
    @SerializedName("post_text") private String postText;
    @SerializedName("post_liked_id") private int[] postLikedBy;
    @SerializedName("post_media") private String mediaURL; //TODO: Change to Image or ImageView???
    @SerializedName("post_replies_id") private Feed replies;
    private int likes;

    public Post(int postID, String postDate, int postCreator, String profileImageURL, String postText,
                String mediaURL, int[] group, Feed replies, int likes) {
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

    public Post(int postCreator, String text, String media) {
        this.postCreator = postCreator;
        this.postText = text;
        this.mediaURL = media;
        this.postLikedBy = null;
        this.replies = null;
    }

    public Post(LinkedTreeMap post) {
        double id = Double.parseDouble(String.valueOf(post.get("id")));
        this.postID = (int) id;
        this.postDate = (String) post.get("date_created");
//        String rawCreator = (String) post.get("post_creator");
//        assert rawCreator != null;
        double creator = Double.parseDouble(String.valueOf(post.get("post_creator")));
        this.postCreator = (int) creator;
        this.postText = (String) post.get("post_text");
        this.postLikedBy = (int[])post.get("post_liked");
        this.replies = (Feed)post.get("post_replies");
//        this.mediaURL = (String) post.get("post_media");
    }

    public int getPostID() {
        return postID;
    }

    public int getPostCreator() {
        return postCreator;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getPostText() {
        return postText;
    }

    public String getMediaURL() {
        return mediaURL;
    }

    public int[] getPostLikedBy() {
        return postLikedBy;
    }

    public Feed getReplies() {
        return replies;
    }

    public int getLikes() {
        return likes;
    }
}
