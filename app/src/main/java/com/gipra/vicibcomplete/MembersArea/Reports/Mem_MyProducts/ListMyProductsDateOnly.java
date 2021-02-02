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
    @SerializedName("orderid")
    @Expose
    private String orderid;
    @SerializedName("customerid")
    @Expose
    private String customerid;

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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
}
