package com.example.dahlia_android.ui.home;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (post details) or error message.
 */
public class PostResult<T> {
    @Nullable
    private PostView success;
    @Nullable
    private Integer error;

    PostResult(@Nullable Integer error) {
        this.error = error;
    }

    PostResult(@Nullable PostView success) {
        this.success = success;
    }

    @Nullable
    PostView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}