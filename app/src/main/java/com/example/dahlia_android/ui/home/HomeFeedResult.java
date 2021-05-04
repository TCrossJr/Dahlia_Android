package com.example.dahlia_android.ui.home;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
public class HomeFeedResult<T> {
    @Nullable
    private HomeFeedView success;
    @Nullable
    private Integer error;

    HomeFeedResult(@Nullable Integer error) {
        this.error = error;
    }

    HomeFeedResult(@Nullable HomeFeedView success) {
        this.success = success;
    }

    @Nullable
    HomeFeedView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}