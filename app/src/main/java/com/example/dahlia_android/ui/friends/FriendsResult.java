package com.example.dahlia_android.ui.friends;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class FriendsResult<T> {
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
    FriendsView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}