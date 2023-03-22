package com.example.farmhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.farmhub.databinding.ActivityOnboardBinding;
import com.example.farmhub.ui.auth.Sign_up_Activity;
import com.example.farmhub.ui.farmhelp.FarmHelp;
import com.example.farmhub.ui.farmvideos.FarmVideo;

public class OnboardActivity extends AppCompatActivity {

    private ActivityOnboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding.farmHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Sign_up_Activity.class);
                startActivity(i);

            }
        });

        binding.farmVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Sign_up_Activity.class);
                startActivity(i);
            }
        });
    }
    }
