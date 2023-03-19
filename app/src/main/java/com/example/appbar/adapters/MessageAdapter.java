package com.example.appbar.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbar.R;
import com.example.appbar.model.inbox.ThreadResponse;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter {

    private static final int TYPE_MESSAGE_SENT = 0;
    private static final int TYPE_MESSAGE_RECEIVED = 1;
    private static final int TYPE_IMAGE_SENT = 2;
    private static final int TYPE_IMAGE_RECEIVED = 3;

    private LayoutInflater inflater;
    private List<ThreadResponse> threads;
    Context context;
    String loggedInUID;


    public MessageAdapter(List<ThreadResponse> threads, Context context,String loggedInUID) {
        this.threads = threads;
        this.context = context;
        this.loggedInUID = loggedInUID;
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

//    @Override
//    public int getItemViewType(int position) {
//        switch(threads.get(position).getViewType()){
//            case 0:
//                return TYPE_MESSAGE_SENT;
//
//            case 1:
//                return TYPE_MESSAGE_RECEIVED;
//
//            case 2:
//                return TYPE_IMAGE_SENT;
//
//            case 3:
//                return TYPE_IMAGE_RECEIVED;
//
//            default:
//                return -1;
//
//        }
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        switch (viewType){
            case TYPE_MESSAGE_SENT:
                view = inflater.inflate(R.layout.item_sent_message,parent,false);
                return new SentMessageHolder(view);
            case TYPE_MESSAGE_RECEIVED:
                view = inflater.inflate(R.layout.item_received_message, parent,false);
                return new ReceivedMessageHolder(view);
            case TYPE_IMAGE_SENT:
                view = inflater.inflate(R.layout.item_sent_image,parent,false);
                return new SentImageHolder(view);
            case TYPE_IMAGE_RECEIVED:
                view = inflater.inflate(R.layout.item_received_image,parent,false);
                return new ReceivedImageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//        switch (threads.get(position).getViewType()){
//            case TYPE_MESSAGE_SENT:
//                String senderMessage = threads.get(position).getText();
//                ((SentMessageHolder) holder).setView(senderMessage);
//                break;
//
//            case TYPE_MESSAGE_RECEIVED:
//                String receiverMessage = threads.get(position).getText();
//                ((ReceivedMessageHolder) holder).setView(receiverMessage);
//                break;
//
//            case TYPE_IMAGE_SENT:
//                String senderImage = threads.get(position).getImage();
//                ((SentImageHolder) holder).setImage(senderImage);
//                break;
//
//            case TYPE_IMAGE_RECEIVED:
//                String receiverImage = threads.get(position).getImage();
//                ((ReceivedImageHolder) holder).setImage(receiverImage);
//                break;
//        }
    }


    @Override
    public int getItemCount() {
        return threads.size();
    }

}
