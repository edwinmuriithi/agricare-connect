package com.example.appbar.ui.farmhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.appbar.R;
import com.example.appbar.databinding.ActivityFarmHelpRecordBinding;
import com.example.appbar.databinding.ActivityFarmHelpUploadBinding;
import com.example.appbar.ui.farmvideos.FarmVideo;
import com.example.appbar.ui.home.HomeActivity;
import com.example.appbar.ui.inbox.InboxActivity;
import com.example.appbar.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

public class FarmHelpUpload extends AppCompatActivity {

    private ActivityFarmHelpUploadBinding binding;
    String filePath;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFarmHelpUploadBinding.inflate(getLayoutInflater());
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

        Intent intent = getIntent();
        filePath = intent.getStringExtra("filepath");
        description = intent.getStringExtra("description");
        File imageFile = new File(filePath);
        binding.photoUpload.setImageURI(Uri.fromFile(imageFile));
        binding.uploadEditText.setText(description);

        binding.myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmHelpUpload.this, ProfileActivity.class));
                finish();
            }
        });
    }
}