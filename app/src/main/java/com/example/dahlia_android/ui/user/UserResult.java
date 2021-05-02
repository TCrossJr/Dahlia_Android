package com.example.dahlia_android.ui.user;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
public class UserResult<T> {
    @Nullable
    private UserView success;
    @Nullable
    private Integer error;

    public UserResult(@Nullable Integer error) {
        this.error = error;
    }

    public UserResult(@Nullable UserView success) {
        this.success = success;
    }

    @Nullable
    public UserView getSuccess() {
        return success;
    }

    @Nullable
    public Integer getError() {
        return error;
    }
}