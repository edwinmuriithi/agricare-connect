package com.example.appbar.adapters;

import static com.example.appbar.model.inbox.ThreadResponse.TYPE_IMAGE_RECEIVED;
import static com.example.appbar.model.inbox.ThreadResponse.TYPE_IMAGE_SENT;
import static com.example.appbar.model.inbox.ThreadResponse.TYPE_MESSAGE_RECEIVED;
import static com.example.appbar.model.inbox.ThreadResponse.TYPE_MESSAGE_SENT;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbar.R;
import com.example.appbar.model.UserDetails;
import com.example.appbar.model.inbox.ThreadResponse;
import com.example.appbar.storage.SharedPreferencesManager;
import com.example.appbar.ui.inbox.InboxActivity;

import java.io.File;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private List<ThreadResponse> threads;
    Context context;


    public MessageAdapter(List<ThreadResponse> threads, Context context) {
        this.threads = threads;
        this.context = context;
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder{

        TextView senderMessage;
        public SentMessageHolder(@NonNull View itemView) {
            super(itemView);

            senderMessage = itemView.findViewById(R.id.sentTxt);

        }
        private void setView(String text){
            senderMessage.setText(text);
        }
    }

    private class SentImageHolder extends RecyclerView.ViewHolder{

        ImageView sentImage;

        public SentImageHolder(@NonNull View itemView) {
            super(itemView);

            sentImage = itemView.findViewById(R.id.sentImageView);
        }
        private void setImage(String image){
            File imageFile = new File(image);
            sentImage.setImageURI(Uri.fromFile(imageFile));
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder{

        TextView receivedMessage;

        public ReceivedMessageHolder(@NonNull View itemView) {
            super(itemView);
            receivedMessage = itemView.findViewById(R.id.receivedTxt);
        }
        private void setView(String text){
            receivedMessage.setText(text);
        }
    }

    private class ReceivedImageHolder extends RecyclerView.ViewHolder{

        ImageView receivedImage;

        public ReceivedImageHolder(@NonNull View itemView) {
            super(itemView);

            receivedImage = itemView.findViewById(R.id.receivedImageView);
        }
        private void setImage(String image){
            File imageFile = new File(image);
            receivedImage.setImageURI(Uri.fromFile(imageFile));
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch(threads.get(position).getViewType()){
            case 0:
                return TYPE_MESSAGE_SENT;

            case 1:
                return TYPE_MESSAGE_RECEIVED;

            case 2:
                return TYPE_IMAGE_SENT;

            case 3:
                return TYPE_IMAGE_RECEIVED;

            default:
                return -1;

        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        switch (viewType){
            case TYPE_MESSAGE_SENT:
                View senderMessage = LayoutInflater.from(context).inflate(R.layout.item_sent_message,parent,false);
                return new SentMessageHolder(senderMessage);
            case TYPE_MESSAGE_RECEIVED:
                View receiverMessage = LayoutInflater.from(context).inflate(R.layout.item_received_message, parent,false);
                return new ReceivedMessageHolder(receiverMessage);
            case TYPE_IMAGE_SENT:
                View senderImage = LayoutInflater.from(context).inflate(R.layout.item_sent_image,parent,false);
                return new SentImageHolder(senderImage);
            case TYPE_IMAGE_RECEIVED:
                View receivedImage = LayoutInflater.from(context).inflate(R.layout.item_received_image,parent,false);
                return new ReceivedImageHolder(receivedImage);

            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (threads.get(position).getViewType()){
            case TYPE_MESSAGE_SENT:
                String senderMessage = threads.get(position).getText();
                ((SentMessageHolder) holder).setView(senderMessage);
                break;

            case TYPE_MESSAGE_RECEIVED:
                String receiverMessage = threads.get(position).getText();
                ((ReceivedMessageHolder) holder).setView(receiverMessage);
                break;

            case TYPE_IMAGE_SENT:
                String senderImage = threads.get(position).getImage();
                ((SentImageHolder) holder).setImage(senderImage);
                break;

            case TYPE_IMAGE_RECEIVED:
                String receiverImage = threads.get(position).getImage();
                ((ReceivedImageHolder) holder).setImage(receiverImage);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return threads.size();
    }
}


//        JSONObject message = messages.get(position);
//        try {
//            if (message.getBoolean("isSent")){
//                if (message.has("text")){
//                    SentMessageHolder messageHolder = (SentMessageHolder) holder;
//                    messageHolder.messageTxt.setText(message.getString("text"));
//                }else{
//                    SentImageHolder imageHolder = (SentImageHolder) holder;
//                    Bitmap bitmap = getBitmapFromString("image");
//
//                    imageHolder.imageView.setImageBitmap(bitmap);
//                }
//            }else{
//                if(message.has("text")){
//                    ReceivedMessageHolder messageHolder = (ReceivedMessageHolder) holder;
//                    messageHolder.messageText.setText(message.getString("text"));
//                } else{
//                    ReceivedImageHolder imageHolder = (ReceivedImageHolder) holder;
//
//                    Bitmap bitmap = getBitmapFromString(message.getString("image"));
//                    imageHolder.imageView.setImageBitmap(bitmap);
//                }
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//ItemViewType

//      JSONObject message = messages.get(position);
//
//        try {
//            if (message.getBoolean("isSent")){
//                if (message.has("text"))
//                    return TYPE_MESSAGE_SENT;
//                else
//                    return TYPE_IMAGE_SENT;
//            }else{
//                if (message.has("text"))
//                    return TYPE_MESSAGE_RECEIVED;
//                else
//                    return TYPE_IMAGE_RECEIVED;
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return -1;


