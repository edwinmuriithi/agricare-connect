
package com.example.appbar.model.threads;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ThreadResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("threads")
    @Expose
    private List<Object> threads;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ThreadResponse() {
    }

    /**
     * 
     * @param threads
     * @param status
     */
    public ThreadResponse(String status, List<Object> threads) {
        super();
        this.status = status;
        this.threads = threads;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Object> getThreads() {
        return threads;
    }

    public void setThreads(List<Object> threads) {
        this.threads = threads;
    }

}
