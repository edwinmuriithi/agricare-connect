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
import android.app.Activity;
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
import android.widget.Button;
import android.widget.Toast;

import com.example.appbar.R;
import com.example.appbar.databinding.ActivityFarmHelpRecordBinding;
import com.example.appbar.databinding.ActivityFarmhelpBinding;
import com.example.appbar.ui.farmvideos.FarmVideo;
import com.example.appbar.ui.home.HomeActivity;
import com.example.appbar.ui.inbox.InboxActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FarmHelpRecord extends AppCompatActivity {

    private ActivityFarmHelpRecordBinding binding;
    Button ChooseButton;
    // Image request code for onActivityResult() .
    int Image_Request_Code = 7;
    String currentImagePath = null;
    private  static final int IMAGE_REQUEST =1;




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
        ChooseButton = (Button)findViewById(R.id.gallery);

        // Adding click listener to Choose image button.
        ChooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating intent.
                Intent intent = new Intent();

                // Setting intent type as image to select image from phone storage.
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);

            }
        });



        binding.record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();

            }
        });
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
                startActivityForResult(cameraIntent, IMAGE_REQUEST);

                Intent i = new Intent(getApplicationContext(), FarmHelpExplain.class);
                startActivity(i);

            }

        }
    }


    private  File getImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "jpg_"+timeStamp+"_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

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
//        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PIC_REQUEST);
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
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//            if (resultCode == RESULT_OK) {
//                if (requestCode == CAMERA_PIC_REQUEST) {
//                    if (data != null) {
//                        Bitmap photo = (Bitmap) data.getExtras().get("data");
//                        /* Passing BITMAP to the Second Activity */
//                        Intent IntentCamera = new Intent(this, FarmHelpExplain.class);
//                        IntentCamera.putExtra("BitmapImage", photo);
//                        startActivity(IntentCamera);
//                    }
//                } else if (requestCode == REQUEST_GALLERY) {
//                    if (data != null) {
//                        Uri selectedImgUri = data.getData();
//                        /* Passing ImageURI to the Second Activity */
//                        Intent IntentGallery = new Intent(this, FarmHelpExplain.class);
//                        IntentGallery.setData(selectedImgUri);
//                        startActivity(IntentGallery);
//                    }
//
//                }
//            }
