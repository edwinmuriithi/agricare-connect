
package com.example.appbar.model.profile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProfileResponse {

    @SerializedName("data")
    @Expose
    private ProfileRequest data;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProfileResponse() {
    }

    /**
     * 
     * @param data
     * @param status
     */
    public ProfileResponse(ProfileRequest data, String status) {
        super();
        this.data = data;
        this.status = status;
    }

    public ProfileRequest getData() {
        return data;
    }

    public void setData(ProfileRequest data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
