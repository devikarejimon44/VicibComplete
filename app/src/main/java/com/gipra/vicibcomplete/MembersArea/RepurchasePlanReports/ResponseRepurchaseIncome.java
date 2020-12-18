package com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRepurchaseIncome {
    @SerializedName("data")
    @Expose
    private List<ListRepurchaseIncome> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<ListRepurchaseIncome> getData() {
        return data;
    }

    public void setData(List<ListRepurchaseIncome> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
