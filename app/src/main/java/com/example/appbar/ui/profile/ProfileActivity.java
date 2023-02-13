package com.example.appbar.ui.profile;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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


        UserDetails userDetails = SharedPreferencesManager.getInstance(this).getUser();
        binding.fname.setText(userDetails.getNames());
        Log.d(TAG, "Name is " + userDetails.getNames());
        binding.phone.setText(userDetails.getPhone());
        binding.email.setText(userDetails.getEmail());


    }

    private void fetchUserDetails() {
        ProfileRequest profileRequest = new ProfileRequest();

    }
}