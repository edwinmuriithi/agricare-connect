
package com.example.appbar.model.message;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageResponse {

    @SerializedName("posts")
    @Expose
    private List<Object> posts;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MessageResponse() {
    }

    /**
     * 
     * @param posts
     * @param status
     */
    public MessageResponse(List<Object> posts, String status) {
        super();
        this.posts = posts;
        this.status = status;
    }

    public List<Object> getPosts() {
        return posts;
    }

    public void setPosts(List<Object> posts) {
        this.posts = posts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
