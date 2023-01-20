
package com.example.appbar.model.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

   private String phone;
   private String names;
   private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
