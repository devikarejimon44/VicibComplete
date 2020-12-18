package com.gipra.vicibcomplete.MembersArea.PremiumPlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PremiumListTeamSalesBVMatching {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("d_to_date")
    @Expose
    private String dToDate;
    @SerializedName("n_left_pv")
    @Expose
    private String nLeftPv;
    @SerializedName("n_right_pv")
    @Expose
    private String nRightPv;
    @SerializedName("n_pair_pv")
    @Expose
    private String nPairPv;
    @SerializedName("n_left_carry_pv")
    @Expose
    private String nLeftCarryPv;
    @SerializedName("n_right_carry_pv")
    @Expose
    private String nRightCarryPv;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDToDate() {
        return dToDate;
    }

    public void setDToDate(String dToDate) {
        this.dToDate = dToDate;
    }

    public String getNLeftPv() {
        return nLeftPv;
    }

    public void setNLeftPv(String nLeftPv) {
        this.nLeftPv = nLeftPv;
    }

    public String getNRightPv() {
        return nRightPv;
    }

    public void setNRightPv(String nRightPv) {
        this.nRightPv = nRightPv;
    }

    public String getNPairPv() {
        return nPairPv;
    }

    public void setNPairPv(String nPairPv) {
        this.nPairPv = nPairPv;
    }

    public String getNLeftCarryPv() {
        return nLeftCarryPv;
    }

    public void setNLeftCarryPv(String nLeftCarryPv) {
        this.nLeftCarryPv = nLeftCarryPv;
    }

    public String getNRightCarryPv() {
        return nRightCarryPv;
    }

    public void setNRightCarryPv(String nRightCarryPv) {
        this.nRightCarryPv = nRightCarryPv;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
