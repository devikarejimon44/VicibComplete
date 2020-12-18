package com.gipra.vicibcomplete.MembersArea.Gene;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePremiumGenealogy {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<ListPremiumPlanGenealogy> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ListPremiumPlanGenealogy> getData() {
        return data;
    }

    public void setData(List<ListPremiumPlanGenealogy> data) {
        this.data = data;
    }

}
