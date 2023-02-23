package com.example.appbar.ui.inbox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.appbar.R;
import com.example.appbar.adapters.MessageAdapter;
import com.example.appbar.databinding.ActivityFarmVideoBinding;
import com.example.appbar.databinding.ActivityInboxBinding;
import com.example.appbar.model.UserDetails;
import com.example.appbar.storage.SharedPreferencesManager;
import com.example.appbar.ui.farmhelp.FarmHelp;
import com.example.appbar.ui.farmvideos.FarmVideo;
import com.example.appbar.ui.home.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Base64;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class InboxActivity extends AppCompatActivity implements TextWatcher {

    private ActivityInboxBinding binding;
    private WebSocket webSocket;
    private String name;
    private String SERVER_PATH = "https://farmhub-api.dalasystems.com/messaging";
    private int GALLERY_REQUEST_CODE = 5;
    private MessageAdapter messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInboxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

//        UserDetails userDetails = SharedPreferencesManager.getInstance(this).getUser();
        initiateSocketConnection();


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

    private void initiateSocketConnection() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(SERVER_PATH).build();
        webSocket = client.newWebSocket(request,new SocketListener());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String string = s.toString().trim();
        if (string.isEmpty()){
            resetMessageEdit();
        }else {
            binding.sendBtn.setVisibility(View.VISIBLE);
            binding.pickImgBtn.setVisibility(View.INVISIBLE);
        }
    }

    private void resetMessageEdit() {
        binding.messageEdit.removeTextChangedListener(this);

        binding.messageEdit.setText("");
        binding.sendBtn.setVisibility(View.INVISIBLE);
        binding.pickImgBtn.setVisibility(View.VISIBLE);

        binding.messageEdit.addTextChangedListener(this);
    }

    private class SocketListener extends WebSocketListener {
        @Override
        public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
            super.onMessage(webSocket, text);

            runOnUiThread(() -> {
                try {
                    JSONObject jsonObject = new JSONObject(text);
                    jsonObject.put("isSent",false);

                    messageAdapter.addItem(jsonObject);

                    binding.chatrecycle.smoothScrollToPosition(messageAdapter.getItemCount() -1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        }

        @Override
        public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
            super.onOpen(webSocket, response);

            runOnUiThread(() -> {
                Toast.makeText(InboxActivity.this, "Socket Connection Successful", Toast.LENGTH_SHORT).show();
                initializeView();
            });
        }
    }

    private void initializeView() {
        messageAdapter = new MessageAdapter(getLayoutInflater());
        binding.chatrecycle.setAdapter(messageAdapter);
        binding.chatrecycle.setLayoutManager(new LinearLayoutManager(this));

        binding.messageEdit.addTextChangedListener(this);

        binding.sendBtn.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("text",binding.messageEdit.getText().toString());

                webSocket.send(jsonObject.toString());

                jsonObject.put("isSent", true);
                messageAdapter.addItem(jsonObject);

                binding.chatrecycle.smoothScrollToPosition(messageAdapter.getItemCount() -1);

                resetMessageEdit();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        binding.pickImgBtn.setOnClickListener(v ->{
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");

            startActivityForResult(Intent.createChooser(intent,"Pick Image"),GALLERY_REQUEST_CODE);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK){
            try {
                InputStream is = getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(is);

                sendImage(image);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void sendImage(Bitmap image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,50,outputStream);

//        String base64String = Base64.getEncoder().encodeToString(outputStream.toByteArray(),Base64.getEncoder());
        JSONObject jsonObject = new JSONObject();
        try {

//        jsonObject.put("image", base64String);

            webSocket.send(jsonObject.toString());

            jsonObject.put("isSent", true);

            messageAdapter.addItem(jsonObject);

            binding.chatrecycle.smoothScrollToPosition(messageAdapter.getItemCount() -1);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}