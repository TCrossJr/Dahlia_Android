package com.example.dahlia_android.ui.home;

import java.util.ArrayList;
import java.util.List;

public class Feed extends ArrayList{

    private ArrayList<Post> homeFeed;

    public Feed() {
        homeFeed = new ArrayList<>();
    }

    public Feed(ArrayList<Post> feed) {
        homeFeed = feed;
    }

    public List<Post> getFeed() {
        return homeFeed;
    }

    public Post getPost(int position) {
        return (Post) this.get(position);
    }

    public void addPost( Post post ) {
        homeFeed.add(post);
    }

    public void deletePost( Post post ) { homeFeed.remove(post); }

    public ArrayList<Post> getHomeFeed() {
        return homeFeed;
    }
}
