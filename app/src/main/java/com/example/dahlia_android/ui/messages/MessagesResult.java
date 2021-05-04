package com.example.dahlia_android.ui.messages;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (message details) or error message.
 */
public class MessagesResult<T> {
    @Nullable
    private MessagesView success;
    @Nullable
    private Integer error;

    MessagesResult(@Nullable Integer error) {
        this.error = error;
    }

    MessagesResult(@Nullable MessagesView success) {
        this.success = success;
    }

    @Nullable
    MessagesView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}