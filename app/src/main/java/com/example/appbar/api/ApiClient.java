package com.example.appbar.api;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.appbar.storage.SharedPreferencesManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://farmhub-api.dalasystems.com/";
    private final Context context;
    SharedPreferencesManager sharedPreferencesManager;

    public ApiClient(Context context) {
        this.context = context;
    }

    private static Retrofit getRetrofit(String token) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest;
                newRequest = request.newBuilder()
                        .addHeader("Authorization", "Bearer "+ token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();


      Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
      return retrofit;
    }

    public static UserService getUserService(String token){
        UserService userService = getRetrofit(token).create(UserService.class);
        return userService;
    }
}
