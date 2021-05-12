package com.example.dahlia_android.ui.home;

/**
 * Class exposing authenticated feed details to the UI.
 */
class PostView {
    private Post _post;
    //... other data fields that may be accessible to the UI

    PostView(Post post) {
        this._post = new Post(post);
    }

    Post getPost() {
        return _post;
    }
}