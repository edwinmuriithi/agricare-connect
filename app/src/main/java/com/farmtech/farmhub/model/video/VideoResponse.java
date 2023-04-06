package com.farmtech.farmhub.model.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResponse {

    @SerializedName("media")
    @Expose
    private List<VideoData> media;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     *
     */
    public VideoResponse() {
    }

    /**
     *
     * @param media
     * @param status
     */
    public VideoResponse(List<VideoData> media, String status) {
        super();
        this.media = media;
        this.status = status;
    }

    public List<VideoData> getMedia() {
        return media;
    }

    public void setMedia(List<VideoData> media) {
        this.media = media;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}