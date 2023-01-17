package com.example.appbar.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.example.appbar.databinding.ActivitySignUp2Binding;

public class Sign_up_Activity extends AppCompatActivity {

    private String userName;
    private ActivitySignUp2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUp2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view ==binding.signupBtn){
                    createNewUser();
                }
            }
        });

        binding.loginSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sign_up_Activity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }

    private void createNewUser() {
        userName = binding.fname.getText().toString().trim();

        //capture input from user.
        String name = binding.fname.getText().toString().trim();
        String phone = binding.signUpPhone.getText().toString().trim();
        String confirmPhone = binding.confirmPhone.getText().toString().trim();
        String password = binding.signupPassword.getText().toString().trim();
        String confirmPassword = binding.confirmPassword.getText().toString().trim();

        boolean validPhone = isValidPhone(phone, confirmPhone);
        boolean validUserName = isValidUserName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validPhone || !validUserName || !validPassword) return;
//
//        Call<ResponseBody> call = RetrofitClient
//                .getInstance()
//                .getApi()
//                .registerUser(name,phone,password);
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                //                    if(response.body() != null) {
//                String responseServer = response.body().toString();
//                Toast.makeText(Sign_up_Activity.this, responseServer, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(Sign_up_Activity.this, HomeActivity.class);
//                startActivity(intent);
////                    }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(Sign_up_Activity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
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
        return isGoodPhone;
    }


}









