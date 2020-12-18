package com.gipra.vicibcomplete.MembersArea.District;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDistrict {
    @SerializedName("data")
    @Expose
    private List<DistrictList> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<DistrictList> getData() {
        return data;
    }

    public void setData(List<DistrictList> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
