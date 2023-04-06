package com.farmtech.farmhub.ui.farmhelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.farmtech.farmhub.R;
import com.farmtech.farmhub.databinding.ActivityFarmHelpExplainBinding;
import com.farmtech.farmhub.model.UserDetails;
import com.farmtech.farmhub.storage.SharedPreferencesManager;
import com.farmtech.farmhub.ui.farmvideos.FarmVideo;
import com.farmtech.farmhub.ui.home.HomeActivity;
import com.farmtech.farmhub.ui.inbox.InboxActivity;
import com.farmtech.farmhub.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;


public class FarmHelpExplain extends AppCompatActivity {

    private ActivityFarmHelpExplainBinding binding;
    String filePath;
    String galleryImage;
    // Creating URI.
    Uri contentUri;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFarmHelpExplainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_farm_help);

        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));

        UserDetails userDetails = SharedPreferencesManager.getInstance(this).getUser();
        binding.profileName.setText(userDetails.getNames());

        Intent intent = getIntent();
        filePath = intent.getStringExtra("filepath");
        if (filePath !=null){
        File imageFile = new File(filePath);
        binding.photoUpload.setImageURI(Uri.fromFile(imageFile));
        }


        Uri contentUri = getIntent().getData();
        if (contentUri !=null) {
            binding.photoUpload.setImageURI(contentUri);
        }


        binding.myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmHelpExplain.this, ProfileActivity.class));
                finish();
            }
        });
        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.navigation_farm_help:

                    return true;
                case R.id.navigation_farm_videos:
                    startActivity(new Intent(getApplicationContext(), FarmVideo.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.navigation_inbox:
                    startActivity(new Intent(getApplicationContext(), InboxActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;

        });
        // Assigning Id to ProgressDialog.
        progressDialog = new ProgressDialog(FarmHelpExplain.this);

        // Adding click listener to Upload image button.
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (filePath != null){
                Intent photoIntent = new Intent(FarmHelpExplain.this, FarmHelpUpload.class);
                photoIntent.putExtra("filepath",filePath);
                photoIntent.putExtra("description",binding.explainEditText.getText().toString());
                startActivity(photoIntent);
                }
                if (contentUri !=null){
                    Intent galleryIntent = new Intent(FarmHelpExplain.this,FarmHelpUpload.class);
                    galleryIntent.setData(contentUri);
                    galleryIntent.putExtra("description",binding.explainEditText.getText().toString());
                    startActivity(galleryIntent);
                }
            }
        });
    }
}
