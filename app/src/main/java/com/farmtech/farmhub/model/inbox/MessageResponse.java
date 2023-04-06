
package com.farmtech.farmhub.model.inbox;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MessageResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("threads")
    @Expose
    private List<ThreadResponse> threads;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MessageResponse() {
    }

    /**
     * 
     * @param threads
     * @param status
     */
    public MessageResponse(String status, List<ThreadResponse> threads) {
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

    public List<ThreadResponse> getThreads() {
        return threads;
    }

    public void setThreads(List<ThreadResponse> threads) {
        this.threads = threads;
    }

}
