package com.example.appbar.api;

import com.example.appbar.model.login.LoginRequest;
import com.example.appbar.model.login.LoginResponse;
import com.example.appbar.model.post.PostRequest;
import com.example.appbar.model.post.PostResponse;
import com.example.appbar.model.signup.RegisterRequest;
import com.example.appbar.model.signup.RegisterResponse;
import com.example.appbar.model.profile.ProfileRequest;
import com.example.appbar.model.profile.ProfileResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface UserService {

    @POST("auth/login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("auth/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @GET("auth/me")
    Call<ProfileResponse> profileUser();

    @Multipart
    @POST("/posts")
    Call<PostResponse> postQuestion(@Part("description") RequestBody description,
                                    @Part("image\"; filename=\"image.png\" ") RequestBody image);



}


