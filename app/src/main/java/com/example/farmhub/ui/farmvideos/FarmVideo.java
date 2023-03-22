package com.example.farmhub.ui.farmvideos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.farmhub.R;
import com.example.farmhub.adapters.VideoAdapter;
import com.example.farmhub.api.ApiClient;
import com.example.farmhub.databinding.ActivityFarmVideoBinding;
import com.example.farmhub.databinding.ActivityFarmhelpBinding;
import com.example.farmhub.model.video.Video;
import com.example.farmhub.model.video.VideoData;
import com.example.farmhub.model.video.VideoResponse;
import com.example.farmhub.ui.farmhelp.FarmHelp;
import com.example.farmhub.ui.home.HomeActivity;
import com.example.farmhub.ui.inbox.InboxActivity;
import com.example.farmhub.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class FarmVideo extends AppCompatActivity {

    private ActivityFarmVideoBinding binding;
    public static final String TAG = "TAG";
    RecyclerView videoList;
    VideoAdapter adapter;
    List<Video> all_videos;
//    List<VideoData> allVideos;

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
        videoList.setLayoutManager(new LinearLayoutManager(FarmVideo.this));
        adapter = new VideoAdapter(this,all_videos);

        videoList.setAdapter(adapter);



        getJsonData();
//        fetchVideos();
    }

    private void fetchVideos() {
        Call<VideoResponse> videoResponseCall = ApiClient.getUserService(this).fetchVideos();
        videoResponseCall.enqueue(new retrofit2.Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, retrofit2.Response<VideoResponse> response) {
                VideoResponse videoResponse = response.body();
                if (response.isSuccessful()){
//                    Toast.makeText(FarmVideo.this, "Successfully fetched videos", Toast.LENGTH_SHORT).show();
//                    allVideos = new ArrayList<>();

//                    videoList = findViewById(R.id.videoList);
//                    videoList.setLayoutManager(new LinearLayoutManager(FarmVideo.this));
//                    adapter = new VideoAdapter(FarmVideo.this,allVideos);
//
//                    videoList.setAdapter(adapter);



                }else{
                    Toast.makeText(FarmVideo.this, "Unable to fetch at the moment", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {

            }
        });
    }

    private void getJsonData() {
//        String URL = "https://raw.githubusercontent.com/bikashthapa01/myvideos-android-app/master/data.json";
        String URL = "https://raw.githubusercontent.com/edwinmuriithi/Java-Upload-Image/master/data.json";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d(TAG, "onResponse: "+ response);
                try {
                    JSONArray categories = response.getJSONArray("categories");
                    JSONObject categoriesData = categories.getJSONObject(0);
                    JSONArray videos = categoriesData.getJSONArray("videos");

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