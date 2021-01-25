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
}
