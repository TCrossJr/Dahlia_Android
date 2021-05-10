package com.example.dahlia_android.ui.groups;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (group details) or error message.
 */
public class GroupsResult<T> {
    @Nullable
    private GroupsView success;
    @Nullable
    private Integer error;

    GroupsResult(@Nullable Integer error) {
        this.error = error;
    }

    GroupsResult(@Nullable GroupsView success) {
        this.success = success;
    }

    @Nullable
    GroupsView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}