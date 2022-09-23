package com.example.appbar.ui.farmvideos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appbar.databinding.FragmentFarmVideoBinding;

public class FarmVideoFragment extends Fragment {

    private FragmentFarmVideoBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FarmVideoViewModel farmVideoViewModel =
                new ViewModelProvider(this).get(FarmVideoViewModel.class);

        binding = FragmentFarmVideoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //Disable darkmode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        final TextView textView = binding.textFarmVideo;
        farmVideoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}