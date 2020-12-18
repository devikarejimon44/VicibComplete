package com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRepurchaseIncomeDetails {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<ListRepurchaseIncomeDetails> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ListRepurchaseIncomeDetails> getData() {
        return data;
    }

    public void setData(List<ListRepurchaseIncomeDetails> data) {
        this.data = data;
    }
}
