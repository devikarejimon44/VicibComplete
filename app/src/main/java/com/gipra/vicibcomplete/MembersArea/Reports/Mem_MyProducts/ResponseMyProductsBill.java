package com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts;

import com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts.ListMyproductsbill;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMyProductsBill {
    @SerializedName("data")
    @Expose
    private List<ListMyproductsbill> data = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Invoice")
    @Expose
    private String invoice;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public List<ListMyproductsbill> getData() {
        return data;
    }

    public void setData(List<ListMyproductsbill> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
