package com.farmtech.farmhub.api;

import com.farmtech.farmhub.model.forgetpass.ForgetPassRequest;
import com.farmtech.farmhub.model.forgetpass.ForgetPassResponse;
import com.farmtech.farmhub.model.inbox.MessageResponse;
import com.farmtech.farmhub.model.login.LoginRequest;
import com.farmtech.farmhub.model.login.LoginResponse;

import com.farmtech.farmhub.model.post.PostResponse;
import com.farmtech.farmhub.model.signup.RegisterRequest;
import com.farmtech.farmhub.model.signup.RegisterResponse;
import com.farmtech.farmhub.model.profile.ProfileResponse;
import com.farmtech.farmhub.model.video.VideoResponse;


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


