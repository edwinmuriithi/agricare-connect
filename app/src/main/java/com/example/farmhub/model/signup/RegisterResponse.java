
package com.example.farmhub.model.signup;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RegisterResponse {

    @SerializedName("user")
    @Expose
    private RegisterRequest registerRequest;
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
     * @param registerRequest
     * @param status
     */
    public RegisterResponse(RegisterRequest registerRequest, String status, String message) {
        super();
        this.registerRequest = registerRequest;
        this.status = status;
        this.message = message;
    }

    public RegisterRequest getUser() {
        return registerRequest;
    }

    public void setUser(RegisterRequest registerRequest) {
        this.registerRequest = registerRequest;
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
