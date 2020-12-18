package com.gipra.vicibcomplete.MembersArea.PremiumPlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePremiumTeamSalesBVMatching {
    @SerializedName("data")
    @Expose
    private List<PremiumListTeamSalesBVMatching> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<PremiumListTeamSalesBVMatching> getData() {
        return data;
    }

    public void setData(List<PremiumListTeamSalesBVMatching> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
