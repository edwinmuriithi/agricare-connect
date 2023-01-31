package com.example.appbar.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import com.example.appbar.databinding.ActivityProfileBinding;
import com.example.appbar.model.UserDetails;
import com.example.appbar.model.profile.ProfileRequest;
import com.example.appbar.storage.SharedPreferencesManager;
import com.example.appbar.ui.auth.LoginActivity;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        fetchUserDetails();

        UserDetails userDetails = SharedPreferencesManager.getInstance(this).getUser();
        binding.fname.setText(userDetails.getNames());
        binding.phone.setText(userDetails.getPhone());
        binding.email.setText(userDetails.getEmail());


    }

    private void fetchUserDetails() {
        ProfileRequest profileRequest = new ProfileRequest();

    }
}