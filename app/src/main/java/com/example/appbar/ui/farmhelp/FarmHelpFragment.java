package com.example.appbar.ui.farmhelp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appbar.databinding.FragmentFarmHelpBinding;

public class FarmHelpFragment extends Fragment {

    private FragmentFarmHelpBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FarmHelpViewModel notificationsViewModel =
                new ViewModelProvider(this).get(FarmHelpViewModel.class);


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding = FragmentFarmHelpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//       final TextView textView = binding.textFarmHelp;
//       notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}