
package com.farmtech.farmhub.model.inbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import tz.co.hosannahighertech.messagekit.commons.models.IMessage;
import tz.co.hosannahighertech.messagekit.commons.models.IUser;


public class ThreadResponse implements IMessage {

//    private final int viewType;

    @SerializedName("senderId")
    @Expose
    private String senderId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("recipientId")
    @Expose
    private String recipientId;
    @SerializedName("read")
    @Expose
    private Boolean read;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("recipient")
    @Expose
    private Recipient recipient;
    @SerializedName("sender")
    @Expose
    private Sender sender;

    /**
     * No args constructor for use in serialization
     *
     * @param viewType
     * @param s
     */

    /**
     * 
     * @param image
     * @param senderId
     * @param read
     * @param sender
     * @param recipient
     * @param recipientId
     * @param text
     * @param updatedAt
     */
    public ThreadResponse(String senderId, String image, String recipientId, Boolean read, String text, String updatedAt, Recipient recipient, Sender sender) {
        super();
        this.senderId = senderId;
        this.image = image;
        this.recipientId = recipientId;
        this.read = read;
        this.text = text;
        this.updatedAt = updatedAt;
        this.recipient = recipient;
        this.sender = sender;
    }


    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    @Override
    public String getId() {
        return null;
    }

    public String getText() {
        return text;
    }

    @Override
    public IUser getUser() {
        return null;
    }

    @Override
    public Date getCreatedAt() {
        return null;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

}
