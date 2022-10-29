package com.example.appbar.ui.farmhelp;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appbar.R;
import com.example.appbar.databinding.ActivityFarmHelpExplainBinding;
import com.example.appbar.databinding.ActivityFarmHelpRecordBinding;
import com.example.appbar.ui.farmvideos.FarmVideo;
import com.example.appbar.ui.home.HomeActivity;
import com.example.appbar.ui.inbox.InboxActivity;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.UUID;

public class FarmHelpExplain extends AppCompatActivity {

   private ActivityFarmHelpExplainBinding binding;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;
    // Folder path for Firebase Storage.
    String Storage_Path = "All_Image_Uploads/";

    // Creating EditText.
    EditText ImageName ;

    // Creating ImageView.
    ImageView SelectImage;


    // Root Database Name for Firebase Database.
    String Database_Path = "All_Image_Uploads_Database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFarmHelpExplainBinding.inflate(getLayoutInflater());
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

        /* Getting ImageURI from Gallery from FarmRecord Activity */
        Uri selectedImgUri = getIntent().getData();
        if (selectedImgUri != null) {
            Log.e("Gallery ImageURI", "" + selectedImgUri);
            String[] selectedImgPath = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImgUri,
                    selectedImgPath, null, null, null);
            cursor.moveToFirst();

            int indexCol = cursor.getColumnIndex(selectedImgPath[0]);
            String imgPath = cursor.getString(indexCol);
            cursor.close();
            binding.photoUpload.setImageBitmap(BitmapFactory.decodeFile(imgPath));
        }

        /* Getting ImageBitmap from Camera from FarmRecord Activity */
        Intent intent_camera = getIntent();
        Bitmap camera_img_bitmap = (Bitmap) intent_camera
                .getParcelableExtra("BitmapImage");
        if (camera_img_bitmap != null) {
            binding.photoUpload.setImageBitmap(camera_img_bitmap);
        }

        storageReference = FirebaseStorage.getInstance().getReference(Storage_Path);
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        ImageName = binding.editTextTextMultiLine;
        SelectImage = binding.photoUpload;
        progressDialog = new ProgressDialog(FarmHelpExplain.this);// context name as per your project name

        
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadImage();

            }
        });
    }

    private void UploadImage() {
        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...");

            // Showing progressDialog.
            progressDialog.show();

            // Creating second StorageReference.
            StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // Getting image name from EditText and store into string variable.
                            String TempImageName = ImageName.getText().toString().trim();

                            // Hiding the progressDialog after done uploading.
                            progressDialog.dismiss();

                            // Showing toast message after done uploading.
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

                            @SuppressWarnings("VisibleForTests")
                            FarmUpload imageUploadInfo = new FarmUpload(TempImageName, taskSnapshot.getStorage().getDownloadUrl().toString());

                            // Getting image upload ID.
                            String ImageUploadId = databaseReference.push().getKey();

                            // Adding image upload id s child element into databaseReference.
                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            progressDialog.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(FarmHelpExplain.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.
                            progressDialog.setTitle("Image is Uploading...");

                        }
                    });
        }
        else {

            Toast.makeText(FarmHelpExplain.this, "Upload failed", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                SelectImage.setImageBitmap(bitmap);


            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }
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
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
//                binding.photoUpload.setImageBitmap(bitmap);
//            }
//            catch (IOException e) {
//
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    public String GetFileExtension(Uri uri) {
//
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;
//
//    }
//
//
//
//    private void UploadImage() {
//        if (FilePathUri != null) {
//
//            progressDialog.setTitle("Image is Uploading...");
//            progressDialog.show();
//            StorageReference storageReference2 = storageReference.child("images/"+ UUID.randomUUID().toString());
//            storageReference2.putFile(FilePathUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
////                            String TempImageName = binding.editTextTextMultiLine.getText().toString().trim();
////                            progressDialog.dismiss();
////                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
////                            @SuppressWarnings("VisibleForTests")
////                            FarmUpload imageUploadInfo = new FarmUpload(TempImageName, taskSnapshot.getUploadSessionUri().toString());
////                            String ImageUploadId = databaseReference.push().getKey();
////                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
//
//                            progressDialog.dismiss();
//                                    Toast.makeText(FarmHelpExplain.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
//                                    String imageUrl = taskSnapshot.getStorage().getDownloadUrl().toString(); /* Fetch url image */
//                                    Picasso.with(getBaseContext()).load(imageUrl).into(binding.photoUpload);
//
//                                    /* use picasso to fetch url and display image */
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    progressDialog.dismiss();
//                                    Toast.makeText(FarmHelpExplain.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                                    double progress_time = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
//                                    progressDialog.setMessage("Uploaded "+(int)progress_time+" %");
//                                }
//                            });
//                        }
//        else {
//
//            Toast.makeText(FarmHelpExplain.this, "Unable to upload", Toast.LENGTH_LONG).show();
//
//        }
//    }
//}
