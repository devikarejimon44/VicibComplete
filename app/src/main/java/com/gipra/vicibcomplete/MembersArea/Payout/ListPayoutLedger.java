package com.gipra.vicibcomplete.MembersArea.Payout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPayoutLedger {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("d_transcation")
    @Expose
    private String dTranscation;
    @SerializedName("c_trans_type")
    @Expose
    private String cTransType;
    @SerializedName("n_trans_amount")
    @Expose
    private String nTransAmount;
    @SerializedName("n_accbalance_after")
    @Expose
    private String nAccbalanceAfter;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDTranscation() {
        return dTranscation;
    }

    public void setDTranscation(String dTranscation) {
        this.dTranscation = dTranscation;
    }

    public String getCTransType() {
        return cTransType;
    }

    public void setCTransType(String cTransType) {
        this.cTransType = cTransType;
    }

    public String getNTransAmount() {
        return nTransAmount;
    }

    public void setNTransAmount(String nTransAmount) {
        this.nTransAmount = nTransAmount;
    }

    public String getNAccbalanceAfter() {
        return nAccbalanceAfter;
    }

    public void setNAccbalanceAfter(String nAccbalanceAfter) {
        this.nAccbalanceAfter = nAccbalanceAfter;
    }

}
