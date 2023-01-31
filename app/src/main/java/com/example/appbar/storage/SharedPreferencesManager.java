package com.example.appbar.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.appbar.model.UserDetails;
import com.example.appbar.model.login.LoginResponse;

public class SharedPreferencesManager {

    private static final String SHARED_PREFERENCES = "my_shared_pref";
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

        editor.putString("id", userDetails.getId());
        editor.putString("names",userDetails.getNames());
        editor.putString("phone", userDetails.getPhone());
        editor.putString("email",userDetails.getEmail());

        editor.apply();

    }
    public void saveToken(String token){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token",token);
        editor.apply();
    }
    public String getToken(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE);
        return sharedPreferences.getString("token",null);
    }

//    public boolean isLoggedIn(){
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
//        return sharedPreferences.getString("id", "-1") != -1;
//    }

    public UserDetails getUser(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE);
        UserDetails userDetails = new UserDetails(
                sharedPreferences.getString("id", null),
                sharedPreferences.getString("names",null),
                sharedPreferences.getString("phone",null),
                sharedPreferences.getString("email",null)
        );
        return userDetails;
    }
    public void clear(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
