package com.gipra.vicibcomplete.MembersArea.State;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseState {
    @SerializedName("data")
    @Expose
    private List<StateList> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<StateList> getData() {
        return data;
    }

    public void setData(List<StateList> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
