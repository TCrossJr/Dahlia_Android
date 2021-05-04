package com.example.dahlia_android.ui.friends;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
public class FriendsResult<T> {
    @Nullable
    private FriendsView success;
    @Nullable
    private Integer error;

    FriendsResult(@Nullable Integer error) {
        this.error = error;
    }

    FriendsResult(@Nullable FriendsView success) {
        this.success = success;
    }

    @Nullable
    public FriendsView getSuccess() {
        return success;
    }

    @Nullable
    public Integer getError() {
        return error;
    }
}