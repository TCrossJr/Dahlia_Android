package com.example.dahlia_android.ui.home;

import java.util.ArrayList;
import java.util.List;

public class HomeFeed extends ArrayList{

    private ArrayList<Post> homeFeed;

    public HomeFeed() {
        homeFeed = new ArrayList<>();
    }

    public List<Post> getFeed() {
        return homeFeed;
    }

    public Post getPost(int position) {
        return (Post) homeFeed.get(position);
    }

    public void addPost( Post post ) {
        homeFeed.add(post);
    }
}
