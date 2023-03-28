package com.example.farmhub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmhub.R;
import com.example.farmhub.model.UserDetails;
import com.example.farmhub.model.inbox.ThreadResponse;
import com.example.farmhub.storage.SharedPreferencesManager;
import com.example.farmhub.ui.inbox.InboxActivity;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter {
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
        return 0;
    }

//    Context context;
//    List<ThreadResponse> messagesArrayList;
//
//    int ITEM_SEND = 1;
//    int ITEM_RECIEVE = 2;
//
//    public MessageAdapter(Context context, ArrayList<ThreadResponse> messagesArrayList) {
//        this.context = context;
//        this.messagesArrayList = messagesArrayList;
//    }
//
//    public MessageAdapter(ArrayList<ThreadResponse> messagesArrayList, InboxActivity inboxActivity, String id) {
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewType == ITEM_SEND) {
//            View view = LayoutInflater.from(context).inflate(R.layout.item_sent_message, parent, false);
//            return new SenderViewHolder(view);
//        } else {
//            View view = LayoutInflater.from(context).inflate(R.layout.item_received_message, parent, false);
//            return new RecieverViewHolder(view);
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//        ThreadResponse messages = messagesArrayList.get(position);
//        if (holder.getClass() == SenderViewHolder.class) {
//            SenderViewHolder viewHolder = (SenderViewHolder) holder;
//            viewHolder.textViewmessaage.setText(messages.getText());
//            viewHolder.timeofmessage.setText(messages.getUpdatedAt());
//        } else {
//            RecieverViewHolder viewHolder = (RecieverViewHolder) holder;
//            viewHolder.textViewmessaage.setText(messages.getText());
//            viewHolder.timeofmessage.setText(messages.getUpdatedAt());
//        }
//
//
//    }
//
//
//    @Override
//    public int getItemViewType(int position) {
//        ThreadResponse messages = messagesArrayList.get(position);
//        UserDetails userDetails = SharedPreferencesManager.getInstance(context).getUser();
//        if (userDetails.getId().equals(messages.getSenderId())) {
//            return ITEM_SEND;
//        } else {
//            return ITEM_RECIEVE;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return messagesArrayList.size();
//    }
//
//
//    class SenderViewHolder extends RecyclerView.ViewHolder {
//
//        TextView textViewmessaage;
//        TextView timeofmessage;
//
//
//        public SenderViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textViewmessaage = itemView.findViewById(R.id.sendermessage);
//            timeofmessage = itemView.findViewById(R.id.timeofmessage);
//        }
//    }
//
//    class RecieverViewHolder extends RecyclerView.ViewHolder {
//
//        TextView textViewmessaage;
//        TextView timeofmessage;
//
//
//        public RecieverViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textViewmessaage = itemView.findViewById(R.id.sendermessage);
//            timeofmessage = itemView.findViewById(R.id.timeofmessage);
//        }
//    }
//

}