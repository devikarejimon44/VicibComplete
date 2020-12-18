package com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRepurchaseBvReport {
    @SerializedName("data")
    @Expose
    private List<ListRepurchaseBVReport> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<ListRepurchaseBVReport> getData() {
        return data;
    }

    public void setData(List<ListRepurchaseBVReport> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
