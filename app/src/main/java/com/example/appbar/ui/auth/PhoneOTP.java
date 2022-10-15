package com.example.appbar.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appbar.databinding.ActivityLoginBinding;
import com.example.appbar.databinding.ActivityPhoneOtpBinding;

public class PhoneOTP extends AppCompatActivity {

    private ActivityPhoneOtpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneOtpBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }
}