
package com.example.appbar.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PostResponse {

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PostResponse() {
    }

    /**
     * 
     * @param image
     * @param imageUrl
     * @param id
     * @param status
     */
    public PostResponse(String imageUrl, String id, String image, String status) {
        super();
        this.imageUrl = imageUrl;
        this.id = id;
        this.image = image;
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
