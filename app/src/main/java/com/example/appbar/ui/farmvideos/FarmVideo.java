package com.example.appbar.ui.farmvideos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbar.R;
import com.example.appbar.adapters.VideoAdapter;
import com.example.appbar.databinding.ActivityFarmVideoBinding;
import com.example.appbar.databinding.ActivityFarmhelpBinding;
import com.example.appbar.model.video.Video;
import com.example.appbar.ui.farmhelp.FarmHelp;
import com.example.appbar.ui.home.HomeActivity;
import com.example.appbar.ui.inbox.InboxActivity;
import com.example.appbar.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FarmVideo extends AppCompatActivity {

    private ActivityFarmVideoBinding binding;
    public static final String TAG = "TAG";
    RecyclerView videoList;
    VideoAdapter adapter;
    List<Video> all_videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFarmVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_farm_videos);

        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));

//        WebSettings webSettings = binding.farmVideo.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        binding.farmVideo.setWebViewClient(new Callback());
//        binding.farmVideo.loadUrl("https://www.youtube.com/user/beeflambnz/videos");


        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.navigation_farm_help:
                    startActivity(new Intent(getApplicationContext(), FarmHelp.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.navigation_farm_videos:
                    return true;

                case R.id.navigation_inbox:
                    startActivity(new Intent(getApplicationContext(), InboxActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;

        });
        binding.myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmVideo.this, ProfileActivity.class));
                finish();
            }
        });

        all_videos = new ArrayList<>();

        videoList = findViewById(R.id.videoList);
        videoList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VideoAdapter(this,all_videos);
        videoList.setAdapter(adapter);
        getJsonData();
    }

    private void getJsonData() {
        String URL = "https://raw.githubusercontent.com/bikashthapa01/myvideos-android-app/master/data.json";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d(TAG, "onResponse: "+ response);
                try {
                    JSONArray categories = response.getJSONArray("categories");
                    JSONObject categoriesData = categories.getJSONObject(0);
                    JSONArray videos = categoriesData.getJSONArray("videos");

                    //Log.d(TAG, "onResponse: "+ videos);

                    for (int i = 0; i< videos.length();i++){
                        JSONObject video = videos.getJSONObject(i);

                        Video v = new Video();


                        v.setTitle(video.getString("title"));
                        v.setDescription(video.getString("description"));
                        v.setAuthor(video.getString("subtitle"));
                        v.setImageUrl(video.getString("thumb"));
                        JSONArray videoUrl = video.getJSONArray("sources");
                        v.setVideoUrl(videoUrl.getString(0));

                        all_videos.add(v);
                        adapter.notifyDataSetChanged();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
            }
        });

        requestQueue.add(objectRequest);
    }

    private  class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }

}