package com.example.appbar.ui.farmvideos;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appbar.databinding.FragmentFarmVideoBinding;

import javax.security.auth.callback.Callback;

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
        WebSettings webSettings = binding.farmVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.farmVideo.setWebViewClient(new Callback());
        binding.farmVideo.loadUrl("https://www.youtube.com/user/beeflambnz/videos");

        return root;
    }
        private  class Callback extends WebViewClient{
            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return false;
            }
        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}