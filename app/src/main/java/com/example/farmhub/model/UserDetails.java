package com.example.farmhub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserDetails {

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
    @SerializedName("paidUser")
    @Expose
    private String paidUser;
    @SerializedName("lastPayment")
    @Expose
    private String lastPayment;
    @SerializedName("nextPayment")
    @Expose
    private String nextPayment;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserDetails() {
    }

    /**
     *
     * @param createdAt
     * @param paidUser
     * @param names
     * @param role
     * @param phone
     * @param lastPayment
     * @param id
     * @param nextPayment
     * @param updatedAt
     */
    public UserDetails(String id, String createdAt, String updatedAt, String names, String role, String phone, String paidUser, String lastPayment, String nextPayment) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.names = names;
        this.role = role;
        this.phone = phone;
        this.paidUser = paidUser;
        this.lastPayment = lastPayment;
        this.nextPayment = nextPayment;
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

    public String getPaidUser() {
        return paidUser;
    }

    public void setPaidUser(String paidUser) {
        this.paidUser = paidUser;
    }

    public String getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(String lastPayment) {
        this.lastPayment = lastPayment;
    }

    public String getNextPayment() {
        return nextPayment;
    }

    public void setNextPayment(String nextPayment) {
        this.nextPayment = nextPayment;
    }

}