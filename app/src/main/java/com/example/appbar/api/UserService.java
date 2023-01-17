package com.example.appbar.api;

import com.example.appbar.model.LoginRequest;
import com.example.appbar.model.LoginResponse;
import com.example.appbar.model.RegisterRequest;
import com.example.appbar.model.RegisterResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    @POST("auth/login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("auth/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
}
