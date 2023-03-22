package com.example.farmhub.ui.profile;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.farmhub.api.ApiClient;
import com.example.farmhub.databinding.ActivityProfileBinding;
import com.example.farmhub.model.UserDetails;
import com.example.farmhub.model.profile.ProfileData;
import com.example.farmhub.model.profile.ProfileResponse;
import com.example.farmhub.storage.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        fetchUserDetails();
        UserDetails userDetails = SharedPreferencesManager.getInstance(this).getUser();
        binding.fname.setText(userDetails.getNames());
        Log.d(TAG, "Name is " + userDetails.getNames());
        binding.phone.setText(userDetails.getPhone());
        Log.d(TAG, "Phone number is " + userDetails.getPhone());
//        binding.email.setText(userDetails.getEmail());
//        Log.d(TAG, "Email is " + userDetails.getEmail());
        binding.payment.setText(userDetails.getPaidUser());
        Log.d(TAG, "Payment Status is " + userDetails.getPaidUser());
        binding.lastPayment.setText(userDetails.getLastPayment());
        binding.nextPayment.setText(userDetails.getNextPayment());


    }

    private void fetchUserDetails() {
        ProfileData profileData = new ProfileData();
        profileData.setNames(binding.fname.getText().toString().trim());
        profileData.setPhone(binding.phone.getText().toString().trim());
        profileData.setEmail(binding.email.getText().toString().trim());
        profileData.setPaidUser(binding.payment.getText().toString().trim());



        Call<ProfileResponse> profileResponseCall = ApiClient.getUserService(this).profileUser();
        profileResponseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                ProfileResponse profileResponse = response.body();
                if(response.isSuccessful()){
                    Toast.makeText(ProfileActivity.this, "Profile Fetched Successfully", Toast.LENGTH_SHORT).show();

                    binding.fname.setText(profileResponse.getData().getNames());
                    Log.d(TAG, "Name is " + profileResponse.getData().getNames());
                    binding.phone.setText(profileResponse.getData().getPhone());
                    Log.d(TAG, "Phone number is " + profileResponse.getData().getPhone());
                    binding.email.setText(profileResponse.getData().getEmail());
                    Log.d(TAG, "Email is " + profileResponse.getData().getEmail());
                    binding.payment.setText(profileResponse.getData().getPaidUser());
                    Log.d(TAG, "Email is " + profileResponse.getData().getPaidUser());

                }else{
                    Toast.makeText(ProfileActivity.this, "Error loading user details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this,"Throwable " +t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    }
