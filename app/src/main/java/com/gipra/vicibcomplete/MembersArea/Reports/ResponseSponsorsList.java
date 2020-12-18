package com.gipra.vicibcomplete.MembersArea.Reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSponsorsList {
    @SerializedName("data")
    @Expose
    private List<ListSponsorsList> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<ListSponsorsList> getData() {
        return data;
    }

    public void setData(List<ListSponsorsList> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
