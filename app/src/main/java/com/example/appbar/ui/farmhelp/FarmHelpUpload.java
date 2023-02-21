package com.example.appbar.ui.farmhelp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmHelpUpload extends AppCompatActivity {

    private ActivityFarmHelpUploadBinding binding;
    String filePath;
    String description;
    File imageFile;

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



        binding.uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostRequest postRequest = new PostRequest();
                postRequest.setDescription(description);
                postRequest.setImage(imageFile);
                uploadFile();
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

    private void uploadFile() {

////        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), postRequest.getImage());
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), postRequest.getImage());
////        MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file",postRequest.getImage().getName(),requestFile);
//        RequestBody descBody = RequestBody.create(MediaType.parse("text/plain"), postRequest.getDescription());


//        File imageFile = new File(filePath);
////        Uri imageUri = Uri.fromFile(imageFile);
//        RequestBody filePart = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
////        RequestBody filePart = RequestBody.create(MediaType.parse(getContentResolver().getType(imageUri)),filePath);
//        MultipartBody.Part file = MultipartBody.Part.createFormData("image",imageFile.getName(),filePart);


        File imageFile = new File(filePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"),imageFile);
        MultipartBody.Part imageBody = MultipartBody.Part.createFormData("image", imageFile.getName(),requestFile);

//        RequestBody fileUpload = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
//        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"),imageFile);

        RequestBody descriptionPart = RequestBody.create(MultipartBody.FORM, description);

        Call<PostResponse> postResponseCall = ApiClient.getUserService(FarmHelpUpload.this).postQuestion(descriptionPart,imageBody);
        postResponseCall.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                PostResponse postResponse = response.body();
                if(response.isSuccessful()){
                    Toast.makeText(FarmHelpUpload.this, "Question submitted successfully", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Question posted Successfully " + postResponse.getImageUrl());
                    Intent activityIntent = new Intent(FarmHelpUpload.this, FarmHelpSuccess.class);
                    startActivity(activityIntent);

                }else{
                    Toast.makeText(FarmHelpUpload.this, "Failed to submit question... Please try again", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error sending image " + postResponse.getStatus());
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Toast.makeText(FarmHelpUpload.this,"Throwable " +t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}