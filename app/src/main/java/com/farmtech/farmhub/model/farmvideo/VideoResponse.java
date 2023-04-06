
package com.farmtech.farmhub.model.farmvideo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class VideoResponse {

    @SerializedName("media")
    @Expose
    private List<Medium> media;
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
    public VideoResponse(List<Medium> media, String status) {
        super();
        this.media = media;
        this.status = status;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}