
package com.farmtech.farmhub.model.forgetpass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ForgetPassResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ForgetPassResponse() {
    }

    /**
     * 
     * @param message
     * @param status
     */
    public ForgetPassResponse(String message, String status) {
        super();
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
