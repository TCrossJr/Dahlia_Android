package com.example.dahlia_android.ui.signup;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class SignUpResult<T>  {
    @Nullable
    private SignedUpUserView success;
    @Nullable
    private Integer error;

    SignUpResult(@Nullable Integer error) {
        this.error = error;
    }

    SignUpResult(@Nullable SignedUpUserView success) {
        this.success = success;
    }

    @Nullable
    SignedUpUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}