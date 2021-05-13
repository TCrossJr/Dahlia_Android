package com.example.dahlia_android.ui.user;

import androidx.annotation.Nullable;

public class UserListResult<T> {
    @Nullable
    private UserListView success;

    @Nullable
    private Integer error;

    private String message;

    UserListResult(@Nullable Integer error) {
        this.error = error;
    }

    UserListResult(@Nullable UserListView success) {
        this.success = success;
    }

    public UserListResult(String message) {
        this.message = message;
    }

    @Nullable
    public UserListView getSuccess() {
        return success;
    }

    @Nullable
    public Integer getError(){
        return error;
    }
}
