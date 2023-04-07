
package com.farmtech.farmhub.model.profile;


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
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("county")
    @Expose
    private String county;
    @SerializedName("subCounty")
    @Expose
    private Object subCounty;
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
     * @param paidUser
     * @param names
     * @param role
     * @param phone
     * @param county
     * @param id
     * @param subCounty
     * @param updatedAt
     */
    public ProfileData(String id, String createdAt, String updatedAt, String names, String role, String phone, String county, Object subCounty, String paidUser) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.names = names;
        this.role = role;
        this.phone = phone;
        this.county = county;
        this.subCounty = subCounty;
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Object getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(Object subCounty) {
        this.subCounty = subCounty;
    }

    public String getPaidUser() {
        return paidUser;
    }

    public void setPaidUser(String paidUser) {
        this.paidUser = paidUser;
    }

}
