package com.example.appbar.ui.farmhelp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appbar.R;
import com.example.appbar.databinding.FragmentFarmHelpRecordBinding;


public class FarmHelpRecordFragment extends Fragment {

    FragmentFarmHelpRecordBinding binding;
    static final int CAMERA_PIC_REQUEST = 1000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFarmHelpRecordBinding.inflate(inflater,container,false);

        binding.record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });
        return binding.getRoot();



    }
}