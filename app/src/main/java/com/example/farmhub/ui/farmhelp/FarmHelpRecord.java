package com.example.farmhub.ui.farmhelp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.farmhub.R;
import com.example.farmhub.databinding.ActivityFarmHelpExplainBinding;
import com.example.farmhub.databinding.ActivityFarmHelpRecordBinding;
import com.example.farmhub.ui.farmvideos.FarmVideo;
import com.example.farmhub.ui.home.HomeActivity;
import com.example.farmhub.ui.inbox.InboxActivity;
import com.example.farmhub.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FarmHelpRecord extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 103;
    private static final String TAG = "MyActivity";
    private ActivityFarmHelpRecordBinding binding;
    int Image_Request_Code = 7;
    public static final int CAMERA_PERMISSION_CODE = 101;
    String currentImagePath;
    public static final int CAMERA_REQUEST_CODE =102;
    public static final int GALLERY_PERMISSION_CODE = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFarmHelpRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_farm_help);

        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));


        binding.myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmHelpRecord.this, ProfileActivity.class));
                finish();
            }
        });
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

        // Adding click listener to Choose image button.
        binding.gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    Intent openGallery = new Intent();
                    openGallery.setType("image/*");
                    openGallery.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(openGallery,GALLERY_REQUEST_CODE);
                }else{
                    ActivityCompat.requestPermissions(FarmHelpRecord.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},GALLERY_PERMISSION_CODE);
                }

            }
        });

        binding.record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askCameraPermission();
            }
        });
    }

    private void askCameraPermission() {
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);

            }else{
                dispatchTakePictureIntent();
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
             File file = new File(currentImagePath);
//             binding.testImg.setImageURI(Uri.fromFile(file));
             Log.d(TAG, "Absolute URL of image is " + Uri.fromFile(file));

             Intent cameraIntent = new Intent(this,FarmHelpExplain.class);
             cameraIntent.putExtra("filepath",file.toString());
             startActivity(cameraIntent);


            }
        }

        if (requestCode == GALLERY_REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                Uri contentUri = data.getData();
                Intent galleryIntent = new Intent(this,FarmHelpExplain.class);
                galleryIntent.setData(contentUri);
                startActivity(galleryIntent);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentImagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }



    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
              dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Camera Permission is required to use camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private  File getImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentImagePath = image.getAbsolutePath();
        return image;

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = getImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }


}
