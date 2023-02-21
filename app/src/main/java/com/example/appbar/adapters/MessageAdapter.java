package com.example.appbar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbar.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter {

    private int TYPE_MESSAGE_SENT = 0;
    private int TYPE_MESSAGE_RECEIVED = 1;
    private int TYPE_IMAGE_SENT = 2;
    private int TYPE_IMAGE_RECEIVED = 3;

    private LayoutInflater inflater;
    private List<JSONObject> messages = new ArrayList<>();

    public MessageAdapter (LayoutInflater inflater){
        this.inflater = inflater;
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder{

        TextView messageTxt;
        public SentMessageHolder(@NonNull View itemView) {
            super(itemView);

            messageTxt = itemView.findViewById(R.id.sentTxt);

        }
    }

    private class SentImageHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public SentImageHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.sentImageView);
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder{

        TextView messageText;

        public ReceivedMessageHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.receivedTxt);
        }
    }

    private class ReceivedImageHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public ReceivedImageHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.receivedImageView);
        }
    }

    @Override
    public int getItemViewType(int position) {
      JSONObject message = messages.get(position);

        try {
            if (message.getBoolean("isSent")){
                if (message.has("text"))
                    return TYPE_MESSAGE_SENT;
                else
                    return TYPE_IMAGE_SENT;
            }else{
                if (message.has("text"))
                    return TYPE_MESSAGE_RECEIVED;
                else
                    return TYPE_IMAGE_RECEIVED;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
