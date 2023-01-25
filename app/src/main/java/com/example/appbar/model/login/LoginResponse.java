
package com.example.appbar.model.login;


import com.example.appbar.model.User;
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
     * @param status
     * @param token
     */
    public LoginResponse(String status, String token, Long issued, Long expires, Boolean newUser) {
        super();
        this.status = status;
        this.token = token;
        this.issued = issued;
        this.expires = expires;
        this.newUser = newUser;
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

    public boolean getNewUser() {
        return newUser;
    }

    public void setNewUser(Boolean newUser) {
        this.newUser = newUser;
    }

}
