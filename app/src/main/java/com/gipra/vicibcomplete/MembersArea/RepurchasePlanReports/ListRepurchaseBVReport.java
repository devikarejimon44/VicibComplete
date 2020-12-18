package com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListRepurchaseBVReport {
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
    @SerializedName("N_PV")
    @Expose
    private String nPV;
    @SerializedName("totalamount")
    @Expose
    private Integer totalamount;
    @SerializedName("total_pv")
    @Expose
    private Integer totalPv;

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

    public String getNPV() {
        return nPV;
    }

    public void setNPV(String nPV) {
        this.nPV = nPV;
    }

    public Integer getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Integer totalamount) {
        this.totalamount = totalamount;
    }

    public Integer getTotalPv() {
        return totalPv;
    }

    public void setTotalPv(Integer totalPv) {
        this.totalPv = totalPv;
    }

}
