package com.example.appbar.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appbar.api.ApiClient;
import com.example.appbar.databinding.ActivityLoginBinding;
import com.example.appbar.model.login.LoginRequest;
import com.example.appbar.model.login.LoginResponse;
import com.example.appbar.storage.SharedPreferencesManager;
import com.example.appbar.ui.home.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;
    private final static int RC_SIGN_IN = 123;

    ProgressDialog progressDialog;

    public static final String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String phone = binding.phoneLogin.getText().toString().trim();
        String password = binding.passwordLogin.getText().toString().trim();


        binding.signuplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, Sign_up_Activity.class);
                startActivity(intent);

            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(binding.phoneLogin.getText().toString().trim() ) || TextUtils.isEmpty(binding.passwordLogin.getText().toString().trim())){
                    Toast.makeText(LoginActivity.this, "Username or Password Incorrect",Toast.LENGTH_LONG).show();
                }else{
                    //proceed
                    login();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPreferencesManager.getInstance(this).isLoggedIn()){
            Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void login(){

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhone(binding.phoneLogin.getText().toString().trim());
        loginRequest.setPassword(binding.passwordLogin.getText().toString().trim());


        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                    SharedPreferencesManager.getInstance(LoginActivity.this).saveUser(loginResponse.getNewUser());


                    Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Incorrect Phone number or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,"Throwable " +t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }



}