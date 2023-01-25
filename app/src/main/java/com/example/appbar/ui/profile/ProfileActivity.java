package com.example.appbar.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import com.example.appbar.databinding.ActivityFarmhelpBinding;
import com.example.appbar.databinding.ActivityProfileBinding;
import com.example.appbar.model.User;
import com.example.appbar.model.profile.ProfileRequest;
import com.example.appbar.storage.SharedPreferencesManager;
import com.example.appbar.ui.auth.LoginActivity;
import com.example.appbar.ui.home.HomeActivity;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        fetchUserDetails();

        User user = SharedPreferencesManager.getInstance(this).getUser();
        binding.fname.setText(user.getNames());


    }
    @Override
    protected void onStart() {
        super.onStart();
        if (!SharedPreferencesManager.getInstance(this).isLoggedIn()){
            Intent intent=new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    private void fetchUserDetails() {
        ProfileRequest profileRequest = new ProfileRequest();

    }
}