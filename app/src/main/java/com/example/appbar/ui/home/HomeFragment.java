package com.example.appbar.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.appbar.R;
import com.example.appbar.databinding.FragmentHomeBinding;
import com.example.appbar.ui.farmhelp.FarmHelpFragment;
import com.example.appbar.ui.farmhelp.FarmHelpRecordFragment;
import com.example.appbar.ui.farmvideos.FarmVideoFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.farmHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FarmHelpFragment farmHelpFragment = new FarmHelpFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_activity_main, farmHelpFragment);
                transaction.commit();
            }
        });

        binding.farmVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FarmVideoFragment farmVideoFragment = new FarmVideoFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_activity_main, farmVideoFragment);
                transaction.commit();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}