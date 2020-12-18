package com.gipra.vicibcomplete.MembersArea.Reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListFirstPurchaseBVReport {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("d_activation")
    @Expose
    private String dActivation;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("n_amount")
    @Expose
    private String nAmount;
    @SerializedName("N_BV")
    @Expose
    private String nBV;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDActivation() {
        return dActivation;
    }

    public void setDActivation(String dActivation) {
        this.dActivation = dActivation;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNAmount() {
        return nAmount;
    }

    public void setNAmount(String nAmount) {
        this.nAmount = nAmount;
    }

    public String getNBV() {
        return nBV;
    }

    public void setNBV(String nBV) {
        this.nBV = nBV;
    }

}
