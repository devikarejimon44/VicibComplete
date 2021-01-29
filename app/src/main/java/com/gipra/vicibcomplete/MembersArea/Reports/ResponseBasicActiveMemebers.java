package com.gipra.vicibcomplete.MembersArea.Reports;

import com.gipra.vicibcomplete.MembersArea.Reports.ListBasicActiveMembers;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBasicActiveMemebers {
    @SerializedName("data")
    @Expose
    private List<ListBasicActiveMembers> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<ListBasicActiveMembers> getData() {
        return data;
    }

    public void setData(List<ListBasicActiveMembers> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
