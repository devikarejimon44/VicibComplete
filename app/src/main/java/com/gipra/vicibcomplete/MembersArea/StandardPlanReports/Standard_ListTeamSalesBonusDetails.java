package com.gipra.vicibcomplete.MembersArea.StandardPlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Standard_ListTeamSalesBonusDetails {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("todate")
    @Expose
    private String todate;
    @SerializedName("n_binary_amt")
    @Expose
    private String nBinaryAmt;
    @SerializedName("gross_amount")
    @Expose
    private String grossAmount;
    @SerializedName("deduction_amount")
    @Expose
    private String deductionAmount;
    @SerializedName("net_amount")
    @Expose
    private String netAmount;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getNBinaryAmt() {
        return nBinaryAmt;
    }

    public void setNBinaryAmt(String nBinaryAmt) {
        this.nBinaryAmt = nBinaryAmt;
    }

    public String getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(String grossAmount) {
        this.grossAmount = grossAmount;
    }

    public String getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(String deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

}
