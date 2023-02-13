package com.example.appbar.api;

import com.example.appbar.model.login.LoginRequest;
import com.example.appbar.model.login.LoginResponse;
import com.example.appbar.model.post.PostRequest;
import com.example.appbar.model.post.PostResponse;
import com.example.appbar.model.signup.RegisterRequest;
import com.example.appbar.model.signup.RegisterResponse;
import com.example.appbar.model.profile.ProfileRequest;
import com.example.appbar.model.profile.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @POST("auth/login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("auth/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @GET("auth/me")
    Call<ProfileResponse> profileUser();

    @POST("/posts")
    Call<PostResponse> postQuestion(@Body PostRequest postRequest); 
}
