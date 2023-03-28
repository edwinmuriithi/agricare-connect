package com.example.farmhub.api;

import com.example.farmhub.model.forgetpass.ForgetPassRequest;
import com.example.farmhub.model.forgetpass.ForgetPassResponse;
import com.example.farmhub.model.inbox.MessageResponse;
import com.example.farmhub.model.inbox.ThreadResponse;
import com.example.farmhub.model.login.LoginRequest;
import com.example.farmhub.model.login.LoginResponse;

import com.example.farmhub.model.post.PostResponse;
import com.example.farmhub.model.signup.RegisterRequest;
import com.example.farmhub.model.signup.RegisterResponse;
import com.example.farmhub.model.profile.ProfileResponse;
import com.example.farmhub.model.video.VideoResponse;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserService {

    @POST("auth/login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("auth/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @GET("auth/me")
    Call<ProfileResponse> profileUser();

    @POST("auth/reset-password")
    Call<ForgetPassResponse> resetPass(@Body ForgetPassRequest forgetPassRequest);

    @Multipart
    @POST("/posts")
    Call<PostResponse> postQuestion(@Part("description") RequestBody description,
                                    @Part MultipartBody.Part image);

    @GET("/messaging")
    Call<MessageResponse>getThreads();

    @GET("/media")
    Call<VideoResponse>fetchVideos();


}


