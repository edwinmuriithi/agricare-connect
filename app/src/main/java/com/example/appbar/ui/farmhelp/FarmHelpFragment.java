package com.example.appbar.ui.farmhelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;


import com.example.appbar.R;
import com.example.appbar.databinding.FragmentFarmHelpBinding;

public class FarmHelpFragment extends Fragment {

    private FragmentFarmHelpBinding farmHelpBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FarmHelpViewModel notificationsViewModel =
                new ViewModelProvider(this).get(FarmHelpViewModel.class);


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        farmHelpBinding = FragmentFarmHelpBinding.inflate(inflater, container, false);
        View root = farmHelpBinding.getRoot();

        farmHelpBinding.question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            FarmHelpRecordFragment farmHelpRecordFragment = new FarmHelpRecordFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment_activity_main, farmHelpRecordFragment);
            transaction.addToBackStack(null).commit();

            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        farmHelpBinding = null;
    }
}