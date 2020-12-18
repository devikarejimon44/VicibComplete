package com.gipra.vicibcomplete.MembersArea.PremiumPlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePremiumTeamSalesBonusDetails {
    @SerializedName("data")
    @Expose
    private List<PremiumListTeamSalesBonusDetails> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<PremiumListTeamSalesBonusDetails> getData() {
        return data;
    }

    public void setData(List<PremiumListTeamSalesBonusDetails> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
