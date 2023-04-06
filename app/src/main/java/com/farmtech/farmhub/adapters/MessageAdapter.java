package com.farmtech.farmhub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.farmtech.farmhub.R;
import com.farmtech.farmhub.model.UserDetails;
import com.farmtech.farmhub.model.inbox.ThreadResponse;
import com.farmtech.farmhub.storage.SharedPreferencesManager;
import com.farmtech.farmhub.ui.inbox.InboxActivity;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter {

    Context context;
    List<ThreadResponse> messagesArrayList;

    int ITEM_SEND = 1;
    int ITEM_RECIEVE = 2;

    public MessageAdapter(Context context, List<ThreadResponse> messagesArrayList) {
        this.context = context;
        this.messagesArrayList = messagesArrayList;
    }

    public MessageAdapter(List<ThreadResponse> messagesArrayList, InboxActivity inboxActivity, String id) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SEND) {
            View view = LayoutInflater.from(context).inflate(R.layout.senderchatlayout, parent, false);
            return new SenderViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.receiverchatlayout, parent, false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ThreadResponse messages = messagesArrayList.get(position);
        if (holder.getClass() == SenderViewHolder.class) {
            SenderViewHolder viewHolder = (SenderViewHolder) holder;
            viewHolder.textViewmessaage.setText(messages.getText());
            viewHolder.timeofmessage.setText(messages.getUpdatedAt());
        } else {
            RecieverViewHolder viewHolder = (RecieverViewHolder) holder;
            viewHolder.textViewmessaage.setText(messages.getText());
            viewHolder.timeofmessage.setText(messages.getUpdatedAt());
        }


    }


    @Override
    public int getItemViewType(int position) {
        ThreadResponse messages = messagesArrayList.get(position);
        UserDetails userDetails = SharedPreferencesManager.getInstance(context).getUser();
        if (userDetails.getId().equals(messages.getSenderId())) {
            return ITEM_SEND;
        } else {
            return ITEM_RECIEVE;
        }
    }

    @Override
    public int getItemCount() {
        return messagesArrayList == null ? 0 : messagesArrayList.size();

//        return messagesArrayList.size();
    }


    class SenderViewHolder extends RecyclerView.ViewHolder {

        TextView textViewmessaage;
        TextView timeofmessage;


        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewmessaage = itemView.findViewById(R.id.sendermessage);
            timeofmessage = itemView.findViewById(R.id.timeofmessage);
        }
    }

    class RecieverViewHolder extends RecyclerView.ViewHolder {

        TextView textViewmessaage;
        TextView timeofmessage;


        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewmessaage = itemView.findViewById(R.id.receivermessage);
            timeofmessage = itemView.findViewById(R.id.timeofmessage);
        }
    }
    public void swapDataSet(List<ThreadResponse> newData){

        this.messagesArrayList = newData;

        //now, tell the adapter about the update
        notifyDataSetChanged();

    }


}