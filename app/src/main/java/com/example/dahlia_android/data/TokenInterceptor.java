package com.example.dahlia_android.data;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    private String token;

    public TokenInterceptor(String token) {
        this.token = token;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request original = chain.request();

        if( original.url().encodedPath().contains("/login") &&
        original.method().equals("post")) {
            return chain.proceed(original);
        }

        HttpUrl originalHttpUrl = original.url();
        Request.Builder requestBuilder = original.newBuilder()
                .addHeader("Authorization", token )
                .url(originalHttpUrl);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    public String getToken() {
        return token;
    }
}
