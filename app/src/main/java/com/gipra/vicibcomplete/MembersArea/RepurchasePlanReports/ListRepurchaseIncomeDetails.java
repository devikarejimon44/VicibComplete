package com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListRepurchaseIncomeDetails {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("D_FROM")
    @Expose
    private String dFROM;
    @SerializedName("d_to")
    @Expose
    private String dTo;
    @SerializedName("n_bv")
    @Expose
    private String nBv;
    @SerializedName("n_level")
    @Expose
    private String nLevel;
    @SerializedName("n_precentage")
    @Expose
    private String nPrecentage;
    @SerializedName("n_level_commition")
    @Expose
    private String nLevelCommition;
    @SerializedName("totalamount")
    @Expose
    private Integer totalamount;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDFROM() {
        return dFROM;
    }

    public void setDFROM(String dFROM) {
        this.dFROM = dFROM;
    }

    public String getDTo() {
        return dTo;
    }

    public void setDTo(String dTo) {
        this.dTo = dTo;
    }

    public String getNBv() {
        return nBv;
    }

    public void setNBv(String nBv) {
        this.nBv = nBv;
    }

    public String getNLevel() {
        return nLevel;
    }

    public void setNLevel(String nLevel) {
        this.nLevel = nLevel;
    }

    public String getNPrecentage() {
        return nPrecentage;
    }

    public void setNPrecentage(String nPrecentage) {
        this.nPrecentage = nPrecentage;
    }

    public String getNLevelCommition() {
        return nLevelCommition;
    }

    public void setNLevelCommition(String nLevelCommition) {
        this.nLevelCommition = nLevelCommition;
    }

    public Integer getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Integer totalamount) {
        this.totalamount = totalamount;
    }

}
