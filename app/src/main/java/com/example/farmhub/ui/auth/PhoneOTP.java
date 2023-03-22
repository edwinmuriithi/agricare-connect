package com.example.farmhub.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.farmhub.databinding.ActivityLoginBinding;
import com.example.farmhub.databinding.ActivityPhoneOtpBinding;

public class PhoneOTP extends AppCompatActivity {

    private ActivityPhoneOtpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneOtpBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }
}