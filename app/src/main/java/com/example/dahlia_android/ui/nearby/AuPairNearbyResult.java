package com.example.dahlia_android.ui.nearby;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (nearby details) or error message.
 */
public class AuPairNearbyResult<T> {
    @Nullable
    private AuPairNearbyView success;
    @Nullable
    private Integer error;

    AuPairNearbyResult(@Nullable Integer error) {
        this.error = error;
    }

    AuPairNearbyResult(@Nullable AuPairNearbyView success) {
        this.success = success;
    }

    @Nullable
    public AuPairNearbyView getSuccess() {
        return success;
    }

    @Nullable
    public Integer getError() {
        return error;
    }
}