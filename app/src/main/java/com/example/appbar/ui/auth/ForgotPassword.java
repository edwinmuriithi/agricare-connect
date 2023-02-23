package com.example.appbar.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appbar.R;
import com.example.appbar.api.ApiClient;
import com.example.appbar.databinding.ActivityFarmHelpExplainBinding;
import com.example.appbar.databinding.ActivityForgotPasswordBinding;
import com.example.appbar.model.forgetpass.ForgetPassRequest;
import com.example.appbar.model.forgetpass.ForgetPassResponse;
import com.example.appbar.ui.home.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    private ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.resetPhone.getText().toString().isEmpty()){
                    Toast.makeText(ForgotPassword.this, "Enter Phone Number!", Toast.LENGTH_LONG).show();
                }else {
                    resetPassword();
                }
            }
        });

        binding.backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void resetPassword() {

        ForgetPassRequest forgetPassRequest = new ForgetPassRequest();
        forgetPassRequest.setPhone(binding.resetPhone.getText().toString().trim());

        Call<ForgetPassResponse> forgetPassResponseCall = ApiClient.getUserService(this).resetPass(forgetPassRequest);
        forgetPassResponseCall.enqueue(new Callback<ForgetPassResponse>() {
            @Override
            public void onResponse(Call<ForgetPassResponse> call, Response<ForgetPassResponse> response) {
              ForgetPassResponse forgetPassResponse = response.body();
              if(response.isSuccessful()){
                Toast.makeText(ForgotPassword.this, "A password reset link has been sent to your phone", Toast.LENGTH_LONG).show();

                  Intent intent=new Intent(ForgotPassword.this, LoginActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  startActivity(intent);
              }else{
                  Toast.makeText(ForgotPassword.this, "Enter correct number", Toast.LENGTH_LONG).show();
              }
            }

            @Override
            public void onFailure(Call<ForgetPassResponse> call, Throwable t) {
                Toast.makeText(ForgotPassword.this, "Unable to reset, Please try again..", Toast.LENGTH_LONG).show();
            }
        });
    }
}