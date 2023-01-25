package com.example.appbar.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.appbar.model.User;

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

    public void saveUser(User user){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", Integer.parseInt(user.getId()));
        editor.putString("names",user.getNames());
        editor.putString("phone", user.getPhone());
        editor.putString("email",user.getEmail());

        editor.apply();

    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id",-1) != -1;
    }

    public User getUser(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE);
        User user = new User(
                sharedPreferences.getString("id", String.valueOf(-1)),
                sharedPreferences.getString("names",null),
                sharedPreferences.getString("phone",null),
                sharedPreferences.getString("email",null)
        );
        return user;
    }
    public void clear(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
