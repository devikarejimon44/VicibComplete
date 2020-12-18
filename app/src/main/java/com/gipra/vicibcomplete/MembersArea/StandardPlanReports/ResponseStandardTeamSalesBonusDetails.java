package com.gipra.vicibcomplete.MembersArea.StandardPlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseStandardTeamSalesBonusDetails {
    @SerializedName("data")
    @Expose
    private List<Standard_ListTeamSalesBonusDetails> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Standard_ListTeamSalesBonusDetails> getData() {
        return data;
    }

    public void setData(List<Standard_ListTeamSalesBonusDetails> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
