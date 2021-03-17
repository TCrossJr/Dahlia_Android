package com.example.dahlia_android.ui.home;

import java.util.ArrayList;
import java.util.List;

public class HomeFeed {

    private ArrayList<Post> homeFeed;

    public HomeFeed() { homeFeed = new ArrayList<>(); }

    public List<Post> getFeed() {
        return homeFeed;
    }

    public Post getSinglePost( int position) {
        return (Post) homeFeed.get(position);
    }
}
