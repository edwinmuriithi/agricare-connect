
package com.example.farmhub.model.inbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sender {

    @SerializedName("names")
    @Expose
    private String names;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sender() {
    }

    /**
     * 
     * @param names
     */
    public Sender(String names) {
        super();
        this.names = names;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

}
