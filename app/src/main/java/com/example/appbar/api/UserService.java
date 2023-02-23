package com.example.appbar.api;

import com.example.appbar.model.forgetpass.ForgetPassRequest;
import com.example.appbar.model.forgetpass.ForgetPassResponse;
import com.example.appbar.model.login.LoginRequest;
import com.example.appbar.model.login.LoginResponse;
import com.example.appbar.model.message.MessageRequest;
import com.example.appbar.model.message.MessageResponse;
import com.example.appbar.model.post.PostRequest;
import com.example.appbar.model.post.PostResponse;
import com.example.appbar.model.signup.RegisterRequest;
import com.example.appbar.model.signup.RegisterResponse;
import com.example.appbar.model.profile.ProfileRequest;
import com.example.appbar.model.profile.ProfileResponse;
import com.example.appbar.model.threads.ThreadResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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

    @POST("auth/reset-password")
    Call<ForgetPassResponse> resetPass(@Body ForgetPassRequest forgetPassRequest);

    @Multipart
    @POST("/posts")
    Call<PostResponse> postQuestion(@Part("description") RequestBody description,
                                    @Part MultipartBody.Part image);

    @POST("/messaging")
    Call<MessageResponse> sendMessage(@Body MessageRequest messageRequest);

    @GET("/messaging")
    Call<ThreadResponse> messageThreads();


}


