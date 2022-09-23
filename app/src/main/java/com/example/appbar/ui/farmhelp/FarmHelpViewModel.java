package com.example.appbar.ui.farmhelp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FarmHelpViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FarmHelpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Share your questions on any challenge you are experiencing with your crops and livestock." +
                "Our expert extension officer will give you a solution right here on FarmHelp!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}