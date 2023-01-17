package com.example.appbar.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appbar.R;
import com.example.appbar.api.ApiClient;
import com.example.appbar.databinding.ActivityLoginBinding;
import com.example.appbar.model.LoginRequest;
import com.example.appbar.model.LoginResponse;
import com.example.appbar.ui.home.HomeActivity;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {


    Button login;

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
                    Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);


                }
            }
        });

    }
    public void login(){

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhone(binding.phoneLogin.getText().toString().trim());
        loginRequest.setPassword(binding.passwordLogin.getText().toString().trim());


        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,"Throwable " +t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }



}