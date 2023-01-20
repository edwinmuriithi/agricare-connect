package com.example.appbar.ui.farmhelp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appbar.R;
import com.example.appbar.databinding.ActivityFarmHelpExplainBinding;
import com.example.appbar.ui.farmvideos.FarmVideo;
import com.example.appbar.ui.home.HomeActivity;
import com.example.appbar.ui.inbox.InboxActivity;
import com.example.appbar.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.IOException;


public class FarmHelpExplain extends AppCompatActivity {

    private ActivityFarmHelpExplainBinding binding;
    // Folder path for Firebase Storage.
    String Storage_Path = "All_Image_Uploads/";

    // Root Database Name for Firebase Database.
    String Database_Path = "All_Image_Uploads_Database";


    // Creating URI.
    Uri FilePathUri;


    // Image request code for onActivityResult() .
    int Image_Request_Code = 7;

    ProgressDialog progressDialog ;


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
//        Intent intent = getIntent();
//        Bitmap cameraBitmap = (Bitmap)intent.getParcelableExtra("BitmapImage");
//        if(cameraBitmap != null){
//            binding.photoUpload.setImageBitmap(cameraBitmap);
//        }

        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
//            String imagePath = bundle.getString("filepath");

            int imageId = bundle.getInt("filepath");
            binding.photoUpload.setImageResource(imageId);
            Log.d(TAG, "Absolute URL is  "+imageId);
        }

//        Intent intent = getIntent();
//        String filePath = null;
//        filePath = intent.getStringExtra("filepath").replace("file://","");;
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 8; //down sizing image as it throws OutOfMemory Exception for larger images
//        File imageFile = new File(filePath);
//        if (imageFile.exists()){
//            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
//            binding.photoUpload.setImageBitmap(bitmap);
//        }



//        Intent intent = getIntent();
//        String filepath = intent.getStringExtra("filepath");
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 8; // down sizing image as it throws OutOfMemory Exception for larger images
//        filepath = filepath.replace("file://", ""); // remove to avoid BitmapFactory.decodeFile return null
//        File imgFile = new File(filepath);
//        if (imgFile.exists()) {
//            ImageView imageView = binding.photoUpload;
//            Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
//            imageView.setImageBitmap(bitmap);

        // Assigning Id to ProgressDialog.
        progressDialog = new ProgressDialog(FarmHelpExplain.this);

        // Adding click listener to Upload image button.
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
//
//            FilePathUri = data.getData();
//
//            try {
//
//                // Getting selected image into Bitmap.
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
//
//                // Setting up bitmap selected image into ImageView.
//                binding.photoUpload.setImageBitmap(bitmap);
//
//
//            }
//            catch (IOException e) {
//
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // Creating Method to get the selected image file Extension from File Path URI.
//    public String GetFileExtension(Uri uri) {
//
//        ContentResolver contentResolver = getContentResolver();
//
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//
//        // Returning the file Extension.
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;
//
//    }

//    // Creating UploadImageFileToFirebaseStorage method to upload image on storage.
//    public void UploadImageFileToFirebaseStorage() {
//
//        // Checking whether FilePathUri Is empty or not.
//        if (FilePathUri != null) {
//
//            // Setting progressDialog Title.
//            progressDialog.setTitle("Image is Uploading...");
//
//            // Showing progressDialog.
//            progressDialog.show();
//
//        }
//    }
}
