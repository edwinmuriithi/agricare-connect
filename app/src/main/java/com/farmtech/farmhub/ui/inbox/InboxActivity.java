package com.farmtech.farmhub.ui.inbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.farmtech.farmhub.R;
import com.farmtech.farmhub.adapters.MessageAdapter;
import com.farmtech.farmhub.api.ApiClient;
import com.farmtech.farmhub.databinding.ActivityInboxBinding;
import com.farmtech.farmhub.model.UserDetails;
import com.farmtech.farmhub.model.inbox.MessageResponse;
import com.farmtech.farmhub.model.inbox.ThreadResponse;
import com.farmtech.farmhub.storage.SharedPreferencesManager;
import com.farmtech.farmhub.ui.farmhelp.FarmHelp;
import com.farmtech.farmhub.ui.farmvideos.FarmVideo;
import com.farmtech.farmhub.ui.home.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InboxActivity extends AppCompatActivity{

    private ActivityInboxBinding binding;
    private int GALLERY_REQUEST_CODE = 5;
    private MessageAdapter messageAdapter;
    RecyclerView recyclerView;
    List<ThreadResponse> messagesArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInboxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        recyclerView = binding.chatrecycle;

        fetchThreads();

        UserDetails userDetails = SharedPreferencesManager.getInstance(this).getUser();


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_inbox);

        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));


        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(item -> {
                switch(item.getItemId())
                {
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_farm_help:
                        startActivity(new Intent(getApplicationContext(), FarmHelp.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_farm_videos:
                        startActivity(new Intent(getApplicationContext(), FarmVideo.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_inbox:

                        return true;
                }
                return false;

        });
    }

    private void fetchThreads() {
//        messagesArrayList = new ArrayList<>();

        Call<MessageResponse> messageResponseCall=ApiClient.getUserService(this).getThreads();
        messageResponseCall.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse messageResponse = response.body();
                if (response.isSuccessful()) {
                    List<ThreadResponse> threads = messageResponse.getThreads();
                    messagesArrayList = threads;
                    Collections.sort(messagesArrayList, new Comparator<ThreadResponse>() {
                        @Override
                        public int compare(ThreadResponse o1, ThreadResponse o2) {
                            return o1.getUpdatedAt().compareTo(o2.getUpdatedAt());
                        }
                    });

                    Toast.makeText(InboxActivity.this, "Fetched messages successfully!!", Toast.LENGTH_SHORT).show();

                    messageAdapter = new MessageAdapter(InboxActivity.this,messagesArrayList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(InboxActivity.this));
//                    recyclerView.setNestedScrollingEnabled(false);
                    recyclerView.setAdapter(messageAdapter);
//                    messageAdapter.notifyDataSetChanged();
                    messageAdapter.swapDataSet(messagesArrayList);
//                    refreshList();

                }


            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(InboxActivity.this, "Unable to fetch messages", Toast.LENGTH_SHORT).show();
            }
        });
           }

    private void refreshList() {
        messageAdapter.notifyDataSetChanged();
    }
}