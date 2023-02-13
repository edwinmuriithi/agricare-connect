package com.example.appbar.storage;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.appbar.model.UserDetails;
import com.example.appbar.model.login.LoginResponse;
import com.google.gson.Gson;

public class SharedPreferencesManager {

    private static final String SHARED_PREFERENCES = "my_shared_pref";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    private static SharedPreferencesManager mInstance;
    private Context mContext;


    public SharedPreferencesManager(Context mContext) {
        this.mContext = mContext;
    }

    public static synchronized SharedPreferencesManager getInstance(Context mContext){
        if(mInstance == null){
            mInstance = new SharedPreferencesManager(mContext);
        }
        return mInstance;
    }

    public void saveUser(UserDetails userDetails){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String userDetails1 = gson.toJson(userDetails);
//        editor.putString("id", userDetails.getId());
//        editor.putString("names",userDetails.getNames());
//        editor.putString("phone", userDetails.getPhone());
//        editor.putString("email",userDetails.getEmail());
        editor.putString("userDetail",userDetails1);

        editor.apply();
        Log.d(TAG, "ID is " + userDetails.getId());
        Log.d(TAG, "User name is " + userDetails.getNames());
        Log.d(TAG, "User has been saved as "+ editor);

    }
    public void saveToken(String token){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("token",token).apply();
        Log.d(TAG, "Token has been saved as "+ token);
    }
    public String getToken(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE);
        Log.d(TAG, "Token has been retrieved as " + sharedPreferences);
        return sharedPreferences.getString("token",null);
    }

//    public boolean isLoggedIn(){
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
//        return sharedPreferences.getString("id", "-1") != -1;
//    }

    public UserDetails getUser(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("userDetail","");
        Gson gson = new Gson();
        UserDetails userDetails = gson.fromJson(user, UserDetails.class);
//        UserDetails userDetails = new UserDetails(
//                sharedPreferences.getString("id", null),
//                sharedPreferences.getString("names",""),
//                sharedPreferences.getString("phone",null),
//                sharedPreferences.getString("email",null)
//        );

        Log.d(TAG, "User name is retrieved as " + userDetails);
        return userDetails;

    }
    public void clear(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
