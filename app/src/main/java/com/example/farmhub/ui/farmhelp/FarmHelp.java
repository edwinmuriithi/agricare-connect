package com.example.farmhub.ui.farmhelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.farmhub.R;
import com.example.farmhub.databinding.ActivityFarmhelpBinding;
import com.example.farmhub.databinding.ActivityMainBinding;
import com.example.farmhub.ui.farmvideos.FarmVideo;
import com.example.farmhub.ui.home.HomeActivity;
import com.example.farmhub.ui.inbox.InboxActivity;
import com.example.farmhub.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FarmHelp extends AppCompatActivity {

    private ActivityFarmhelpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFarmhelpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_farm_help);

        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));


        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(item -> {

                switch(item.getItemId())
                {
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_farm_help:

                        return true;
                    case R.id.navigation_farm_videos:
                        startActivity(new Intent(getApplicationContext(), FarmVideo.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_inbox:
                        startActivity(new Intent(getApplicationContext(), InboxActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;

        });

        binding.question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FarmHelpRecord.class));
                overridePendingTransition(0,0);
            }
        });
        binding.myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmHelp.this, ProfileActivity.class));
                finish();

            }
        });
}
}