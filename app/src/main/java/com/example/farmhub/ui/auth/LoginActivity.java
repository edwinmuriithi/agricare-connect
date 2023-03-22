package com.example.farmhub.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.farmhub.api.ApiClient;
import com.example.farmhub.databinding.ActivityLoginBinding;
import com.example.farmhub.model.login.LoginRequest;
import com.example.farmhub.model.login.LoginResponse;
import com.example.farmhub.storage.SharedPreferencesManager;
import com.example.farmhub.ui.home.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;

    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.signuplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, Sign_up_Activity.class);
                startActivity(intent);

            }
        });

        binding.forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgotPassword.class);
                startActivity(intent);
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.phoneLogin.getText().toString().isEmpty() || (binding.passwordLogin.getText().toString().isEmpty())){
                    Toast.makeText(LoginActivity.this, "Please Enter Number and Password",Toast.LENGTH_LONG).show();
                }else{
                    //proceed
                    login();
                }
            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (SharedPreferencesManager.getInstance(this).isLoggedIn()){
//            Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }

    public void login(){

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhone(binding.phoneLogin.getText().toString().trim());
        loginRequest.setPassword(binding.passwordLogin.getText().toString().trim());


        Call<LoginResponse> loginResponseCall = ApiClient.getUserService(this).userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    SharedPreferencesManager.getInstance(LoginActivity.this).saveUser(loginResponse.getUserDetails());
                    SharedPreferencesManager.getInstance(LoginActivity.this).saveToken(loginResponse.getToken());
                    

                    Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Incorrect Phone number or password", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,"Unable to Log In, Please Try Again..",Toast.LENGTH_LONG).show();
            }
        });
    }



}