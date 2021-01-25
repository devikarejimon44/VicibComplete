package com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListMyProducts {
    @SerializedName("bdatetime")
    @Expose
    private String bdatetime;
    @SerializedName("productname")
    @Expose
    private String productname;
    @SerializedName("dp")
    @Expose
    private String dp;
    @SerializedName("bquantity")
    @Expose
    private String bquantity;
    @SerializedName("total_amount")
    @Expose
    private Integer totalAmount;
    @SerializedName("total_bv")
    @Expose
    private Integer totalBv;
    @SerializedName("orderid")
    @Expose
    private String orderid;

    public String getBdatetime() {
        return bdatetime;
    }

    public void setBdatetime(String bdatetime) {
        this.bdatetime = bdatetime;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getBquantity() {
        return bquantity;
    }

    public void setBquantity(String bquantity) {
        this.bquantity = bquantity;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalBv() {
        return totalBv;
    }

    public void setTotalBv(Integer totalBv) {
        this.totalBv = totalBv;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }


}
