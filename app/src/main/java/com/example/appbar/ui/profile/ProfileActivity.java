package com.example.appbar.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

import com.example.appbar.databinding.ActivityFarmhelpBinding;
import com.example.appbar.databinding.ActivityProfileBinding;
import com.example.appbar.model.profile.ProfileRequest;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        fetchUserDetails();
    }

    private void fetchUserDetails() {
        ProfileRequest profileRequest = new ProfileRequest();

    }
}