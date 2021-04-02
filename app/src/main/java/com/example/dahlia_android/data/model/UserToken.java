package com.example.dahlia_android.data.model;

import com.google.gson.annotations.SerializedName;

public class UserToken {

    @SerializedName("csrfToken")
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public UserToken(String token) {
        this.token = token;
    }
}
