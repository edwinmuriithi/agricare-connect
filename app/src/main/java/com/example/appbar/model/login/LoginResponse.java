
package com.example.appbar.model.login;

import com.example.appbar.model.UserDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("issued")
    @Expose
    private Long issued;
    @SerializedName("expires")
    @Expose
    private Long expires;
    @SerializedName("newUser")
    @Expose
    private Boolean newUser;
    @SerializedName("userDetails")
    @Expose
    private UserDetails userDetails;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LoginResponse() {
    }

    /**
     * 
     * @param expires
     * @param newUser
     * @param issued
     * @param userDetails
     * @param status
     * @param token
     */
    public LoginResponse(String status, String token, Long issued, Long expires, Boolean newUser, UserDetails userDetails) {
        super();
        this.status = status;
        this.token = token;
        this.issued = issued;
        this.expires = expires;
        this.newUser = newUser;
        this.userDetails = userDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getIssued() {
        return issued;
    }

    public void setIssued(Long issued) {
        this.issued = issued;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public Boolean getNewUser() {
        return newUser;
    }

    public void setNewUser(Boolean newUser) {
        this.newUser = newUser;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

}
