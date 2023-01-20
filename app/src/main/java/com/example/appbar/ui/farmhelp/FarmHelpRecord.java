package com.example.appbar.ui.farmhelp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import com.example.appbar.R;
import com.example.appbar.databinding.ActivityFarmHelpExplainBinding;
import com.example.appbar.databinding.ActivityFarmHelpRecordBinding;
import com.example.appbar.ui.auth.LoginActivity;
import com.example.appbar.ui.auth.Sign_up_Activity;
import com.example.appbar.ui.farmvideos.FarmVideo;
import com.example.appbar.ui.home.HomeActivity;
import com.example.appbar.ui.inbox.InboxActivity;
import com.example.appbar.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FarmHelpRecord extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 103;
    private static final String TAG = "MyActivity";
    private ActivityFarmHelpRecordBinding binding;
    private ActivityFarmHelpExplainBinding activityFarmHelpExplainBinding;
    // Image request code for onActivityResult() .
    int Image_Request_Code = 7;
    public static final int CAMERA_PERMISSION_CODE = 101;
    String currentImagePath = null;
    public static final int CAMERA_REQUEST_CODE =102;

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
                // Creating intent.
             Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
             startActivityForResult(gallery,GALLERY_REQUEST_CODE);
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
                captureImage();
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
//                Bitmap photo;
//                photo = (Bitmap) data.getExtras().get("data");
//                Intent cameraIntent = new Intent(this,FarmHelpExplain.class);
//                cameraIntent.putExtra("BitmapImage",photo);
//                startActivity(cameraIntent);
                File file = new File(currentImagePath);
                binding.testImg.setImageURI(Uri.fromFile(file));

//                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                Uri contentUri = Uri.fromFile(file);
//                mediaScanIntent.setData(contentUri);
//                this.sendBroadcast(mediaScanIntent);
                Intent cameraIntent = new Intent(FarmHelpRecord.this, FarmHelpExplain.class);
                cameraIntent.putExtra("filepath",Uri.fromFile(file));
                Log.d(TAG, "Absolute Url of image is " + Uri.fromFile(file));
                startActivity(cameraIntent);
            }
        }

        if (requestCode == GALLERY_REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "jpg_" + timeStamp + "_"+getFileExt(contentUri);
                Log.d(TAG, "onActivityResult: Gallery Image Uri " + imageFileName);
                binding.testImg.setImageURI(contentUri);

            }
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//            if (resultCode == RESULT_OK) {
//                if (requestCode == CAMERA_REQUEST_CODE) {
//                    if (data != null) {
//                        Bitmap photo = (Bitmap) data.getExtras().get("data");
//                        /* Passing BITMAP to the Second Activity */
//                        Intent intentCamera = new Intent(this, FarmHelpExplain.class);
//                        intentCamera.putExtra("BitmapImage", photo);
//                        Log.i(TAG, "onActivityResult: Sending photo");
//                        startActivity(intentCamera);
//                        }
//                    }
//                } else if (requestCode == REQUEST_GALLERY) {
//                    if (data != null) {
//                        Uri selectedImgUri = data.getData();
//                        /* Passing ImageURI to the Second Activity */
//                        Intent intentGallery = new Intent(this, FarmHelpExplain.class);
//                        intentGallery.setData(selectedImgUri);
//                        Log.i(TAG, "onActivityResult: Passing Image URI to second activity");
//                        startActivity(intentGallery);
//                    }
//
//                }
//            }}

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_REQUEST_CODE) {
//            if(resultCode ==Activity.RESULT_OK){
//
////                File file = new File(currentImagePath);
////                activityFarmHelpExplainBinding.photoUpload.setImageURI(Uri.fromFile(file));
//            if (file != null) {
//                startActivity(new Intent(FarmHelpRecord.this, FarmHelpExplain.class));
//                finish();
//            } else {
//                Toast.makeText(this, "Please take a photo", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }}


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                captureImage();
            } else {
                Toast.makeText(this, "Camera Permission is required to use camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void captureImage() {
        Intent cameraIntent = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
        if(cameraIntent.resolveActivity(getPackageManager())!=null){
            File imageFile = null;

            try {
                imageFile = getImageFile();
            } catch (IOException e){
                e.printStackTrace();
            }

            if(imageFile!=null){
                Uri imageUri = FileProvider.getUriForFile(this,"com.example.android.fileprovider",imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
          }

        }
    }

    private  File getImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "jpg_"+timeStamp+"_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File imageFile = File.createTempFile(imageName,".jpg",storageDir);
        currentImagePath = imageFile.getAbsolutePath();
        return imageFile;
    }

}














// binding.record.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                askCameraPermissions();
//            }
//        });
//
//    }
//
//    private void askCameraPermissions() {
//        }
//        else {
//            openCamera();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == CAMERA_PIC_REQUEST) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                openCamera();
//            } else {
//                Toast.makeText(this, "Camera Permission is Required to use camera", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void openCamera() {
//        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
//    }
//
