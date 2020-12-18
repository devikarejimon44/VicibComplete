package com.gipra.vicibcomplete.MembersArea.StandardPlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseStandardLeftSideSales {
    @SerializedName("data")
    @Expose
    private List<Standard_ListLeftSideSales> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Standard_ListLeftSideSales> getData() {
        return data;
    }

    public void setData(List<Standard_ListLeftSideSales> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
