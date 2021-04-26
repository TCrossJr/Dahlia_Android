package com.example.dahlia_android.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String BASE_URL = "http://10.0.2.2:8000/";   // Emulator
//    private static final String BASE_URL = "http://127.0.0.1:8000/";  // Android Device Wifi
//    private static final String BASE_URL = "http:10.0.0.216//:8000/";  // Android Device Wifi
//    private static final String BASE_URL = "http://10.0.0.216:8000/";  // Android Device Wifi

    private static Retrofit retrofit2 = null;

    public static Retrofit getClient() {
        if( retrofit2 == null ) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
/*            CookieJar cookieJar = new CookieJar() {
                @Override
                public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {

                }

                @NotNull
                @Override
                public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                    return null;
                }
            };*/
            OkHttpClient client = new OkHttpClient.Builder()
//                    .cookieJar(cookieJar)
                    .addInterceptor(loggingInterceptor)
                    .build();

            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit2;
    }
}
