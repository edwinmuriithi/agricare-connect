package com.example.appbar.ui.farmvideos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FarmVideoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FarmVideoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is farmvideo fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
