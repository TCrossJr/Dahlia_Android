package com.example.dahlia_android.ui.home;

import java.util.ArrayList;

/**
 * Class exposing authenticated feed details to the UI.
 */
class HomeFeedView {
    private Feed _feed;
    //... other data fields that may be accessible to the UI

    HomeFeedView(Feed feed) {
        this._feed = feed;
    }

    HomeFeedView(ArrayList<Post> feed) {
        this._feed = new Feed(feed);
    }

    Feed getFeed() {
        return _feed;
    }
}