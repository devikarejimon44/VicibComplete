package com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMyProductsDate {
    @SerializedName("data")
    @Expose
    private List<ListMyProductsDate> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<ListMyProductsDate> getData() {
        return data;
    }

    public void setData(List<ListMyProductsDate> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
