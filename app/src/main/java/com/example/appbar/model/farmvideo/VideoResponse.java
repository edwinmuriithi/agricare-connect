
package com.example.appbar.model.farmvideo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class VideoResponse {

    @SerializedName("media")
    @Expose
    private List<Medium> media;

    /**
     * No args constructor for use in serialization
     * 
     */
    public VideoResponse() {
    }

    /**
     * 
     * @param media
     */
    public VideoResponse(List<Medium> media) {
        super();
        this.media = media;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }

}
