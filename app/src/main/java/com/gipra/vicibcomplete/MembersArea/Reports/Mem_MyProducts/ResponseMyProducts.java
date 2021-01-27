package com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts;

import com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts.ListMyProducts;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMyProducts {
    @SerializedName("data")
    @Expose
    private List<ListMyProducts> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<ListMyProducts> getData() {
        return data;
    }

    public void setData(List<ListMyProducts> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
