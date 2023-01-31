package com.example.appbar.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.appbar.api.ApiClient;
import com.example.appbar.databinding.ActivitySignUp2Binding;
import com.example.appbar.model.signup.RegisterRequest;
import com.example.appbar.model.signup.RegisterResponse;
import com.example.appbar.storage.SharedPreferencesManager;
import com.example.appbar.ui.home.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sign_up_Activity extends AppCompatActivity {

    private String userName;
    private ActivitySignUp2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUp2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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
                if(TextUtils.isEmpty(binding.fname.getText().toString().trim() ) || TextUtils.isEmpty(binding.signUpPhone.getText().toString().trim()) ||
                   TextUtils.isEmpty(binding.confirmPhone.getText().toString().trim()) || TextUtils.isEmpty(binding.signupPassword.getText().toString().trim()) ||
                   TextUtils.isEmpty(binding.confirmPassword.getText().toString().trim())){
                    Toast.makeText(Sign_up_Activity.this, "Fill in all fields correctly",Toast.LENGTH_LONG).show();
//                //capture input from user.
//                String name = binding.fname.getText().toString().trim();
//                String phone = binding.signUpPhone.getText().toString().trim();
//                String confirmPhone = binding.confirmPhone.getText().toString().trim();
//                String password = binding.signupPassword.getText().toString().trim();
//                String confirmPassword = binding.confirmPassword.getText().toString().trim();
//
//                boolean validPhone = isValidPhone(phone, confirmPhone);
//                boolean validUserName = isValidUserName(name);
//                boolean validPassword = isValidPassword(password, confirmPassword);
//                if (!validPhone || !validUserName || !validPassword) {
                }else{
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setNames(binding.fname.getText().toString().trim());
                    registerRequest.setPhone(binding.signUpPhone.getText().toString().trim());
                    registerRequest.setPassword(binding.signupPassword.getText().toString().trim());
                    createNewUser(registerRequest);
                }
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (SharedPreferencesManager.getInstance(this).isLoggedIn()){
//            Intent intent=new Intent(this, HomeActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }

    private void createNewUser(RegisterRequest registerRequest) {
        userName = binding.fname.getText().toString().trim();


        String token = SharedPreferencesManager.getInstance(this).getToken();
        Call<RegisterResponse> registerResponseCall = ApiClient.getUserService(token).registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    String message = "Registration Successful";
                    Toast.makeText(Sign_up_Activity.this, message, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Sign_up_Activity.this,LoginActivity.class));
                    finish();

                }else{
                     String message = "An error occurred please try again";
                    Toast.makeText(Sign_up_Activity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(Sign_up_Activity.this, "Throwable "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            binding.signupPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            binding.confirmPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private boolean isValidUserName(String name) {
        if (name.equals("")) {
            binding.fname.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPhone(String phone, String confirmPhone) {
        boolean isGoodPhone =(phone != null && Patterns.PHONE.matcher(phone).matches());
        if (phone.length()<10) {
            binding.signUpPhone.setError("Please enter a valid phone number");
            return false;
        }else if(!phone.equals(confirmPhone)){
            binding.confirmPhone.setError("Phone Numbers do not match");
        }
        return true;
    }
}









