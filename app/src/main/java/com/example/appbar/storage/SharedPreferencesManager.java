package com.example.appbar.storage;

import android.content.Context;

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

    public void saveUser()
}
