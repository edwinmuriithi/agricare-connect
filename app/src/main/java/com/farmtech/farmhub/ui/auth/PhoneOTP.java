package com.farmtech.farmhub.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.farmtech.farmhub.databinding.ActivityPhoneOtpBinding;

public class PhoneOTP extends AppCompatActivity {

    private ActivityPhoneOtpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneOtpBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }
}