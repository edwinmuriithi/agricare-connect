package com.farmtech.farmhub.ui.farmhelp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.farmtech.farmhub.R;
import com.farmtech.farmhub.api.ApiClient;
import com.farmtech.farmhub.constants.RealPathUtil;
import com.farmtech.farmhub.databinding.ActivityFarmHelpUploadBinding;
import com.farmtech.farmhub.model.UserDetails;
import com.farmtech.farmhub.model.post.PostResponse;
import com.farmtech.farmhub.storage.SharedPreferencesManager;
import com.farmtech.farmhub.ui.farmvideos.FarmVideo;
import com.farmtech.farmhub.ui.home.HomeActivity;
import com.farmtech.farmhub.ui.inbox.InboxActivity;
import com.farmtech.farmhub.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
    String path;
    private ProgressDialog progressDialog;

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

        UserDetails userDetails = SharedPreferencesManager.getInstance(this).getUser();
        binding.profileName.setText(userDetails.getNames());

        Intent intent = getIntent();
        filePath = intent.getStringExtra("filepath");
        if (filePath !=null) {
            description = intent.getStringExtra("description");
            File imageFile = new File(filePath);
            binding.photoUpload.setImageURI(Uri.fromFile(imageFile));
            binding.uploadEditText.setText(description);
        }

        Uri contentUri = getIntent().getData();
        if (contentUri !=null) {
            description = getIntent().getStringExtra("description");
            binding.photoUpload.setImageURI(contentUri);
            binding.uploadEditText.setText(description);
        }



        binding.uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        Intent intent = getIntent();
        filePath = intent.getStringExtra("filepath");
        if (filePath != null) {
            File imageFile = new File(filePath);
            Uri contentUri = Uri.fromFile(imageFile);
            Log.d(TAG, "uploadFile: is " + contentUri );
            Context context = FarmHelpUpload.this;
            path = RealPathUtil.getRealPath(context, contentUri);

            File file = new File(path);
            RequestBody fileRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", file.getName(), fileRequestBody);

            RequestBody descriptionPart = RequestBody.create(MediaType.parse("multipart/form-data"), description);

            MultipartBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addPart(filePart)
                    .addPart(descriptionPart)
                    .build();

            Call<PostResponse> postResponseCall = ApiClient.getUserService(FarmHelpUpload.this).postQuestion(descriptionPart, filePart);
            postResponseCall.enqueue(new Callback<PostResponse>() {
                @Override
                public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                    PostResponse postResponse = response.body();
                    if (response.isSuccessful()) {
                        progressDialog = new ProgressDialog(FarmHelpUpload.this);
                        progressDialog.setMessage("Submitting question..");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setProgress(0);
                        progressDialog.show();
                        progressDialog.setCancelable(false);
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep(10000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                progressDialog.dismiss();
                                Log.d(TAG, "Question posted Successfully " + postResponse.getImageUrl());
                                Intent activityIntent = new Intent(FarmHelpUpload.this, FarmHelpSuccess.class);
                                startActivity(activityIntent);
                            }
                        }).start();


//                        Toast.makeText(FarmHelpUpload.this, "Question submitted successfully", Toast.LENGTH_LONG).show();


                    } else {
                        Toast.makeText(FarmHelpUpload.this, "Failed to submit question... Please try again", Toast.LENGTH_LONG).show();
//                    Log.d(TAG, "Error sending image " + postResponse.getStatus());
                    }
                }

                @Override
                public void onFailure(Call<PostResponse> call, Throwable t) {
                    Toast.makeText(FarmHelpUpload.this, "Unable to submit question currently", Toast.LENGTH_LONG).show();
//                    Toast.makeText(FarmHelpUpload.this, "Question submitted successfully", Toast.LENGTH_LONG).show();
//                    Intent activityIntent = new Intent(FarmHelpUpload.this, FarmHelpSuccess.class);
//                    startActivity(activityIntent);
                }
            });
        }
        Uri contentUri = getIntent().getData();
        Log.d(TAG, "uploadFile: of gallery is "+contentUri);
        if(contentUri !=null){
            Context context = FarmHelpUpload.this;
            path = RealPathUtil.getRealPath(context, contentUri);

            File file = new File(path);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
            RequestBody descriptionPart = RequestBody.create(MediaType.parse("multipart/form-data"), description);

            Call<PostResponse> postResponseCall = ApiClient.getUserService(FarmHelpUpload.this).postQuestion(descriptionPart, body);
            postResponseCall.enqueue(new Callback<PostResponse>() {
                @Override
                public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                    PostResponse postResponse = response.body();
                    if (response.isSuccessful()) {
                        progressDialog = new ProgressDialog(FarmHelpUpload.this);
                        progressDialog.setMessage("Submitting question..");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setProgress(0);
                        progressDialog.show();
                        progressDialog.setCancelable(false);
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep(10000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                progressDialog.dismiss();
//                                Toast.makeText(FarmHelpUpload.this, "Question submitted successfully", Toast.LENGTH_LONG).show();
                                Log.d(TAG, "Question posted Successfully " + postResponse.getImageUrl());
                                Intent activityIntent = new Intent(FarmHelpUpload.this, FarmHelpSuccess.class);
                                startActivity(activityIntent);
                            }
                        }).start();



                    } else {
                        Toast.makeText(FarmHelpUpload.this, "Failed to submit question... Please try again", Toast.LENGTH_LONG).show();
//                    Log.d(TAG, "Error sending image " + postResponse.getStatus());
                    }
                }

                @Override
                public void onFailure(Call<PostResponse> call, Throwable t) {
                    Toast.makeText(FarmHelpUpload.this, "Unable to submit question currently", Toast.LENGTH_LONG).show();
                }
            });
        }
        }
    }




//                PostRequest postRequest = new PostRequest();
//                postRequest.setDescription(description);
//                postRequest.setImage(imageFile);


////        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), postRequest.getImage());
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), postRequest.getImage());
////        MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file",postRequest.getImage().getName(),requestFile);
//        RequestBody descBody = RequestBody.create(MediaType.parse("text/plain"), postRequest.getDescription());


//        File imageFile = new File(filePath);
////        Uri imageUri = Uri.fromFile(imageFile);
//        RequestBody filePart = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
////        RequestBody filePart = RequestBody.create(MediaType.parse(getContentResolver().getType(imageUri)),filePath);
//        MultipartBody.Part file = MultipartBody.Part.createFormData("image",imageFile.getName(),filePart);


//        File imageFile = new File(filePath);
//        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"),imageFile);
//        MultipartBody.Part imageBody = MultipartBody.Part.createFormData("image", imageFile.getName(),requestFile);
//        RequestBody fileUpload = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
//        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"),imageFile);

