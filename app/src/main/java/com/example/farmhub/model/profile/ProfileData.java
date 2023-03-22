
package com.example.farmhub.model.profile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileData {

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
    @SerializedName("paidUser")
    @Expose
    private String paidUser;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProfileData() {
    }

    /**
     *
     * @param createdAt
     * @param names
     * @param paidUser
     * @param role
     * @param phone
     * @param id
     * @param email
     * @param updatedAt
     */
    public ProfileData(String id, String createdAt, String updatedAt, String names, String email, String role, String phone,String paidUser) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.names = names;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.paidUser = paidUser;
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
    public String getPaidUser() {
        return paidUser;
    }

    public void setPaidUser(String paidUser) {
        this.paidUser = paidUser;
    }


}
