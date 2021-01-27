package com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListMyproductsbill {
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
    @SerializedName("Invoice")
    @Expose
    private String invoice;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("cgstrate")
    @Expose
    private String cgstrate;
    @SerializedName("cgstamount")
    @Expose
    private String cgstamount;
    @SerializedName("sgstrate")
    @Expose
    private String sgstrate;
    @SerializedName("sgstamount")
    @Expose
    private String sgstamount;

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

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCgstrate() {
        return cgstrate;
    }

    public void setCgstrate(String cgstrate) {
        this.cgstrate = cgstrate;
    }

    public String getCgstamount() {
        return cgstamount;
    }

    public void setCgstamount(String cgstamount) {
        this.cgstamount = cgstamount;
    }

    public String getSgstrate() {
        return sgstrate;
    }

    public void setSgstrate(String sgstrate) {
        this.sgstrate = sgstrate;
    }

    public String getSgstamount() {
        return sgstamount;
    }

    public void setSgstamount(String sgstamount) {
        this.sgstamount = sgstamount;
    }

}
