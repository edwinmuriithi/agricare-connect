
package com.example.appbar.model.profile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileRequest {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("names")
    @Expose
    private String names;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("phone")
    @Expose
    private String phone;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProfileRequest() {
    }

    /**
     * 
     * @param createdAt
     * @param names
     * @param role
     * @param phone
     * @param id
     * @param email
     * @param updatedAt
     */
    public ProfileRequest(String id, String createdAt, String updatedAt, String names, String email, String role, String phone) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.names = names;
        this.email = email;
        this.role = role;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
