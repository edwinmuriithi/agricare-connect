
package com.farmtech.farmhub.model.profile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProfileResponse {

    @SerializedName("data")
    @Expose
    private ProfileData data;
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
    public ProfileResponse(ProfileData data, String status) {
        super();
        this.data = data;
        this.status = status;
    }

    public ProfileData getData() {
        return data;
    }

    public void setData(ProfileData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
