package com.example.appbar.api;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

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


    private static Retrofit getRetrofit(Context context) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        String token = SharedPreferencesManager.getInstance(context).getToken();
        Log.d(TAG, "Token is retrieved from APIClient as " + token);

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

    public static UserService getUserService(Context context){
        UserService userService = getRetrofit(context).create(UserService.class);
        return userService;
    }
}
