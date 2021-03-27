package com.example.dahlia_android.data;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/** TODO: Not currently used, plan to implement soon*/
public class LoginInterceptor implements Interceptor {

    private String credentials;

    public String getCredentials() {
        return credentials;
    }

    public LoginInterceptor(String username, String password) {
        this.credentials = Credentials.basic(username, password);
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        String authToken = getCredentials();
        Headers headers = request.headers().newBuilder()
                .add("Authorization", authToken )
                .add("Content-Type", "application/x-www-form-urlencoded" )
                .build();
        request = request.newBuilder().headers(headers).build();
        return chain.proceed(request);
    }
}
