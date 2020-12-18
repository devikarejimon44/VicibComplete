package com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListDownlineRepurchaseDetails {
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("count2")
    @Expose
    private Integer count2;
    @SerializedName("d_activation")
    @Expose
    private String dActivation;
    @SerializedName("c_username")
    @Expose
    private String cUsername;
    @SerializedName("n_amount")
    @Expose
    private String nAmount;
    @SerializedName("N_PV")
    @Expose
    private String nPV;
    @SerializedName("levelbv")
    @Expose
    private Integer levelbv;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount2() {
        return count2;
    }

    public void setCount2(Integer count2) {
        this.count2 = count2;
    }

    public String getDActivation() {
        return dActivation;
    }

    public void setDActivation(String dActivation) {
        this.dActivation = dActivation;
    }

    public String getCUsername() {
        return cUsername;
    }

    public void setCUsername(String cUsername) {
        this.cUsername = cUsername;
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

    public Integer getLevelbv() {
        return levelbv;
    }

    public void setLevelbv(Integer levelbv) {
        this.levelbv = levelbv;
    }

}
