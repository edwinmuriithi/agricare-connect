package com.example.appbar.ui.farmhelp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appbar.R;
import com.example.appbar.databinding.FragmentFarmHelpRecordBinding;


public class FarmHelpRecordFragment extends Fragment {

    FragmentFarmHelpRecordBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFarmHelpRecordBinding.inflate(inflater,container,false);
        return binding.getRoot();
        

    }
}