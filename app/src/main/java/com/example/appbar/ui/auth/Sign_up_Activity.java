package com.example.appbar.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.appbar.api.ApiClient;
import com.example.appbar.databinding.ActivitySignUp2Binding;
import com.example.appbar.model.UserDetails;
import com.example.appbar.model.signup.RegisterRequest;
import com.example.appbar.model.signup.RegisterResponse;
import com.example.appbar.storage.SharedPreferencesManager;
import com.example.appbar.ui.home.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sign_up_Activity extends AppCompatActivity {

    private String userName;
    SharedPreferences sharedPreferences;
    public static final String myPreferences = "myPref";
    private ActivitySignUp2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUp2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);
        String UserName = binding.fname.getText().toString();
        String phone = binding.signUpPhone.getText().toString();
        if (sharedPreferences.contains(UserName)){
            binding.fname.setText(sharedPreferences.getString(UserName,""));
        }
        if (sharedPreferences.contains(phone)){
            binding.signUpPhone.setText(sharedPreferences.getString(phone,""));
        }

        binding.loginSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sign_up_Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.fname.getText().toString().isEmpty() || binding.signUpPhone.getText().toString().isEmpty() ||
                   binding.confirmPhone.getText().toString().isEmpty() || binding.signupPassword.getText().toString().isEmpty() ||
                   binding.confirmPassword.getText().toString().isEmpty()){
                    Toast.makeText(Sign_up_Activity.this, "Fill in all fields correctly",Toast.LENGTH_LONG).show();

                }else{
                    if(binding.signUpPhone.getText().toString().trim().equals(binding.confirmPhone.getText().toString().trim())) {
                        if (binding.signupPassword.getText().toString().trim().equals(binding.confirmPassword.getText().toString().trim())) {
                            RegisterRequest registerRequest = new RegisterRequest();
                            registerRequest.setNames(binding.fname.getText().toString().trim());
                            registerRequest.setPhone(binding.signUpPhone.getText().toString().trim());
                            registerRequest.setPassword(binding.signupPassword.getText().toString().trim());
                            createNewUser(registerRequest);
                        } else {
                            Toast.makeText(Sign_up_Activity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(Sign_up_Activity.this, "Phone Numbers do not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

        private void createNewUser(RegisterRequest registerRequest) {
        userName = binding.fname.getText().toString().trim();

        Call<RegisterResponse> registerResponseCall = ApiClient.getUserService(this).registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    String message = "Registration Successful";
                    Toast.makeText(Sign_up_Activity.this, message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Sign_up_Activity.this,LoginActivity.class));
                    finish();

                }else{
                     String message = "An error occurred please try again";
                    Toast.makeText(Sign_up_Activity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(Sign_up_Activity.this, "Throwable "+ t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}









