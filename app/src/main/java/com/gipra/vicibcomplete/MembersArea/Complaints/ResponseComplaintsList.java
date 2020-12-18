package com.gipra.vicibcomplete.MembersArea.Complaints;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseComplaintsList {
    @SerializedName("data")
    @Expose
    private List<ListComplaintList> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<ListComplaintList> getData() {
        return data;
    }

    public void setData(List<ListComplaintList> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
