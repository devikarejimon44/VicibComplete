package com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListMyProductsDateOnly {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("bdatetime")
    @Expose
    private String bdatetime;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getBdatetime() {
        return bdatetime;
    }

    public void setBdatetime(String bdatetime) {
        this.bdatetime = bdatetime;
    }

}
