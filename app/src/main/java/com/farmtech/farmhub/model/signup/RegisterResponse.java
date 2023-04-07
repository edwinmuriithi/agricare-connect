
package com.farmtech.farmhub.model.signup;


import com.farmtech.farmhub.model.UserDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RegisterResponse {

    @SerializedName("user")
    @Expose
    private UserDetails userDetails;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RegisterResponse() {
    }

    /**
     * 
     * @param message
     * @param userDetails
     * @param status
     */
    public RegisterResponse(UserDetails userDetails, String status, String message) {
        super();
        this.userDetails = userDetails;
        this.status = status;
        this.message = message;
    }

    public UserDetails getUser() {
        return userDetails;
    }

    public void setUser(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
