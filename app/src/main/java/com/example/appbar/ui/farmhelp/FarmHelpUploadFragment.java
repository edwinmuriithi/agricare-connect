package com.example.appbar.ui.farmhelp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appbar.R;
import com.example.appbar.databinding.FragmentFarmHelpRecordBinding;

public class FarmHelpUploadFragment extends Fragment {

    FragmentFarmHelpRecordBinding binging;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_farm_help_upload, container, false);
    }
}