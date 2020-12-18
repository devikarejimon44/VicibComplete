package com.gipra.vicibcomplete.MembersArea.PremiumPlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePremiumListLeftSideSales {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<PremiumListLeftSideSales> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PremiumListLeftSideSales> getData() {
        return data;
    }

    public void setData(List<PremiumListLeftSideSales> data) {
        this.data = data;
    }
}
