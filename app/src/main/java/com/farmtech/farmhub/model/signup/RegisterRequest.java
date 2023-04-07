
package com.farmtech.farmhub.model.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RegisterRequest {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("county")
    @Expose
    private String county;
    @SerializedName("subCounty")
    @Expose
    private String subCounty;
    @SerializedName("names")
    @Expose
    private String names;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RegisterRequest() {
    }

    /**
     * 
     * @param password
     * @param names
     * @param phone
     * @param county
     * @param subCounty
     */
    public RegisterRequest(String phone, String password, String county, String subCounty, String names) {
        super();
        this.phone = phone;
        this.password = password;
        this.county = county;
        this.subCounty = subCounty;
        this.names = names;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(String subCounty) {
        this.subCounty = subCounty;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

}
