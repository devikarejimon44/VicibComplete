package com.gipra.vicibcomplete.MembersArea.ui.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Responsedashboard {
    @SerializedName("c_fname")
    @Expose
    private String cFname;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("sponsor_id")
    @Expose
    private String sponsorId;
    @SerializedName("d_join")
    @Expose
    private String dJoin;
    @SerializedName("dayscompleted")
    @Expose
    private Integer dayscompleted;
    @SerializedName("premium_direct_referals")
    @Expose
    private Integer premiumDirectReferals;
    @SerializedName("std_direct_referals")
    @Expose
    private Integer stdDirectReferals;
    @SerializedName("left_team")
    @Expose
    private String leftTeam;
    @SerializedName("right_team")
    @Expose
    private String rightTeam;
    @SerializedName("total_team")
    @Expose
    private Integer totalTeam;
    @SerializedName("current_std_bv")
    @Expose
    private Integer currentStdBv;
    @SerializedName("current_premium_bv")
    @Expose
    private Integer currentPremiumBv;
    @SerializedName("repurchase_current_mnth")
    @Expose
    private Integer repurchaseCurrentMnth;
    @SerializedName("activated_date")
    @Expose
    private String activatedDate;
    @SerializedName("total_left_bv")
    @Expose
    private String totalLeftBv;
    @SerializedName("total_right_bv")
    @Expose
    private String totalRightBv;
    @SerializedName("total_bv")
    @Expose
    private Integer totalBv;
    @SerializedName("total_pair_bv")
    @Expose
    private String totalPairBv;
    @SerializedName("current_left_bv")
    @Expose
    private Integer currentLeftBv;
    @SerializedName("current_right_bv")
    @Expose
    private Integer currentRightBv;
    @SerializedName("total_business_left")
    @Expose
    private String totalBusinessLeft;
    @SerializedName("total_business_right")
    @Expose
    private String totalBusinessRight;
    @SerializedName("silver_activated_on")
    @Expose
    private String silverActivatedOn;
    @SerializedName("total_left_bv1")
    @Expose
    private String totalLeftBv1;
    @SerializedName("total_right_bv1")
    @Expose
    private String totalRightBv1;
    @SerializedName("total_bv1")
    @Expose
    private Integer totalBv1;
    @SerializedName("total_pair_bv1")
    @Expose
    private String totalPairBv1;
    @SerializedName("current_left_bv1")
    @Expose
    private Integer currentLeftBv1;
    @SerializedName("current_right_bv1")
    @Expose
    private Integer currentRightBv1;
    @SerializedName("todays_business_left_bv1")
    @Expose
    private String todaysBusinessLeftBv1;
    @SerializedName("todays_business_right_bv1")
    @Expose
    private String todaysBusinessRightBv1;
    @SerializedName("total_earnings")
    @Expose
    private Integer totalEarnings;
    @SerializedName("gold_earnings")
    @Expose
    private Integer goldEarnings;
    @SerializedName("silver_earnings")
    @Expose
    private Integer silverEarnings;
    @SerializedName("repurchase_earnings")
    @Expose
    private Integer repurchaseEarnings;
    @SerializedName("thismonth_repurchase_earnings")
    @Expose
    private Integer thismonthRepurchaseEarnings;
    @SerializedName("status")
    @Expose
    private String status;

    public String getCFname() {
        return cFname;
    }

    public void setCFname(String cFname) {
        this.cFname = cFname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getDJoin() {
        return dJoin;
    }

    public void setDJoin(String dJoin) {
        this.dJoin = dJoin;
    }

    public Integer getDayscompleted() {
        return dayscompleted;
    }

    public void setDayscompleted(Integer dayscompleted) {
        this.dayscompleted = dayscompleted;
    }

    public Integer getPremiumDirectReferals() {
        return premiumDirectReferals;
    }

    public void setPremiumDirectReferals(Integer premiumDirectReferals) {
        this.premiumDirectReferals = premiumDirectReferals;
    }

    public Integer getStdDirectReferals() {
        return stdDirectReferals;
    }

    public void setStdDirectReferals(Integer stdDirectReferals) {
        this.stdDirectReferals = stdDirectReferals;
    }

    public String getLeftTeam() {
        return leftTeam;
    }

    public void setLeftTeam(String leftTeam) {
        this.leftTeam = leftTeam;
    }

    public String getRightTeam() {
        return rightTeam;
    }

    public void setRightTeam(String rightTeam) {
        this.rightTeam = rightTeam;
    }

    public Integer getTotalTeam() {
        return totalTeam;
    }

    public void setTotalTeam(Integer totalTeam) {
        this.totalTeam = totalTeam;
    }

    public Integer getCurrentStdBv() {
        return currentStdBv;
    }

    public void setCurrentStdBv(Integer currentStdBv) {
        this.currentStdBv = currentStdBv;
    }

    public Integer getCurrentPremiumBv() {
        return currentPremiumBv;
    }

    public void setCurrentPremiumBv(Integer currentPremiumBv) {
        this.currentPremiumBv = currentPremiumBv;
    }

    public Integer getRepurchaseCurrentMnth() {
        return repurchaseCurrentMnth;
    }

    public void setRepurchaseCurrentMnth(Integer repurchaseCurrentMnth) {
        this.repurchaseCurrentMnth = repurchaseCurrentMnth;
    }

    public String getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(String activatedDate) {
        this.activatedDate = activatedDate;
    }

    public String getTotalLeftBv() {
        return totalLeftBv;
    }

    public void setTotalLeftBv(String totalLeftBv) {
        this.totalLeftBv = totalLeftBv;
    }

    public String getTotalRightBv() {
        return totalRightBv;
    }

    public void setTotalRightBv(String totalRightBv) {
        this.totalRightBv = totalRightBv;
    }

    public Integer getTotalBv() {
        return totalBv;
    }

    public void setTotalBv(Integer totalBv) {
        this.totalBv = totalBv;
    }

    public String getTotalPairBv() {
        return totalPairBv;
    }

    public void setTotalPairBv(String totalPairBv) {
        this.totalPairBv = totalPairBv;
    }

    public Integer getCurrentLeftBv() {
        return currentLeftBv;
    }

    public void setCurrentLeftBv(Integer currentLeftBv) {
        this.currentLeftBv = currentLeftBv;
    }

    public Integer getCurrentRightBv() {
        return currentRightBv;
    }

    public void setCurrentRightBv(Integer currentRightBv) {
        this.currentRightBv = currentRightBv;
    }

    public String getTotalBusinessLeft() {
        return totalBusinessLeft;
    }

    public void setTotalBusinessLeft(String totalBusinessLeft) {
        this.totalBusinessLeft = totalBusinessLeft;
    }

    public String getTotalBusinessRight() {
        return totalBusinessRight;
    }

    public void setTotalBusinessRight(String totalBusinessRight) {
        this.totalBusinessRight = totalBusinessRight;
    }

    public String getSilverActivatedOn() {
        return silverActivatedOn;
    }

    public void setSilverActivatedOn(String silverActivatedOn) {
        this.silverActivatedOn = silverActivatedOn;
    }

    public String getTotalLeftBv1() {
        return totalLeftBv1;
    }

    public void setTotalLeftBv1(String totalLeftBv1) {
        this.totalLeftBv1 = totalLeftBv1;
    }

    public String getTotalRightBv1() {
        return totalRightBv1;
    }

    public void setTotalRightBv1(String totalRightBv1) {
        this.totalRightBv1 = totalRightBv1;
    }

    public Integer getTotalBv1() {
        return totalBv1;
    }

    public void setTotalBv1(Integer totalBv1) {
        this.totalBv1 = totalBv1;
    }

    public String getTotalPairBv1() {
        return totalPairBv1;
    }

    public void setTotalPairBv1(String totalPairBv1) {
        this.totalPairBv1 = totalPairBv1;
    }

    public Integer getCurrentLeftBv1() {
        return currentLeftBv1;
    }

    public void setCurrentLeftBv1(Integer currentLeftBv1) {
        this.currentLeftBv1 = currentLeftBv1;
    }

    public Integer getCurrentRightBv1() {
        return currentRightBv1;
    }

    public void setCurrentRightBv1(Integer currentRightBv1) {
        this.currentRightBv1 = currentRightBv1;
    }

    public String getTodaysBusinessLeftBv1() {
        return todaysBusinessLeftBv1;
    }

    public void setTodaysBusinessLeftBv1(String todaysBusinessLeftBv1) {
        this.todaysBusinessLeftBv1 = todaysBusinessLeftBv1;
    }

    public String getTodaysBusinessRightBv1() {
        return todaysBusinessRightBv1;
    }

    public void setTodaysBusinessRightBv1(String todaysBusinessRightBv1) {
        this.todaysBusinessRightBv1 = todaysBusinessRightBv1;
    }

    public Integer getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Integer totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public Integer getGoldEarnings() {
        return goldEarnings;
    }

    public void setGoldEarnings(Integer goldEarnings) {
        this.goldEarnings = goldEarnings;
    }

    public Integer getSilverEarnings() {
        return silverEarnings;
    }

    public void setSilverEarnings(Integer silverEarnings) {
        this.silverEarnings = silverEarnings;
    }

    public Integer getRepurchaseEarnings() {
        return repurchaseEarnings;
    }

    public void setRepurchaseEarnings(Integer repurchaseEarnings) {
        this.repurchaseEarnings = repurchaseEarnings;
    }

    public Integer getThismonthRepurchaseEarnings() {
        return thismonthRepurchaseEarnings;
    }

    public void setThismonthRepurchaseEarnings(Integer thismonthRepurchaseEarnings) {
        this.thismonthRepurchaseEarnings = thismonthRepurchaseEarnings;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
