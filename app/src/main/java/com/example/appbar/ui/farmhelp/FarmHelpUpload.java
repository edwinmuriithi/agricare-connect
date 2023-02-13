package com.example.appbar.ui.farmhelp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.appbar.R;
import com.example.appbar.api.ApiClient;
import com.example.appbar.databinding.ActivityFarmHelpRecordBinding;
import com.example.appbar.databinding.ActivityFarmHelpUploadBinding;
import com.example.appbar.model.post.PostRequest;
import com.example.appbar.model.post.PostResponse;
import com.example.appbar.model.profile.ProfileResponse;
import com.example.appbar.ui.farmvideos.FarmVideo;
import com.example.appbar.ui.home.HomeActivity;
import com.example.appbar.ui.inbox.InboxActivity;
import com.example.appbar.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        PostRequest postRequest = new PostRequest();

        binding.uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRequest.setDescription(description);
                postRequest.setImage(imageFile);
                Call<PostResponse> postResponseCall = ApiClient.getUserService(FarmHelpUpload.this).postQuestion(postRequest);
                postResponseCall.enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        PostResponse postResponse = response.body();
                        if(response.isSuccessful()){
                            Toast.makeText(FarmHelpUpload.this, "Question posted Successfully", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Image uploaded successfully " + postResponse.getImageUrl());

                        }else{
                            Toast.makeText(FarmHelpUpload.this, "Error sending image", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Failed to upload image " + postResponse.getStatus());
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        Toast.makeText(FarmHelpUpload.this,"Throwable " +t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }

        });

        binding.myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmHelpUpload.this, ProfileActivity.class));
                finish();
            }
        });
    }
}