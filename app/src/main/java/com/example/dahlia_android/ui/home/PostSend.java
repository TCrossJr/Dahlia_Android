package com.example.dahlia_android.ui.home;

import com.google.gson.annotations.SerializedName;

public class PostSend {

    @SerializedName("post_creator_id") private int postCreatorID;
    @SerializedName("post_text") private String postText;
    @SerializedName("post_media") private String mediaURL; //TODO: Change to Image or ImageView???

    public PostSend(int postCreator, String postText) {
        this.postCreatorID = postCreator;
        this.postText = postText;
    }

    public PostSend(int postCreator, String postText, String mediaURL) {
        this.postCreatorID = postCreator;
        this.postText = postText;
        this.mediaURL = mediaURL;
    }

    public int getPostCreatorID() {
        return postCreatorID;
    }

    public String getPostText() {
        return postText;
    }

    public String getMediaURL() {
        return mediaURL;
    }
}
