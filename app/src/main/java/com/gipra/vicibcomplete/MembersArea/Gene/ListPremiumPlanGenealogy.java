package com.gipra.vicibcomplete.MembersArea.Gene;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPremiumPlanGenealogy {
    @SerializedName("postion")
    @Expose
    private String postion;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("left_count")
    @Expose
    private String leftCount;
    @SerializedName("right_count")
    @Expose
    private String rightCount;
    @SerializedName("left_active")
    @Expose
    private String leftActive;
    @SerializedName("right_active")
    @Expose
    private String rightActive;
    @SerializedName("left_pv")
    @Expose
    private String leftPv;
    @SerializedName("right_pv")
    @Expose
    private String rightPv;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sponsor_id")
    @Expose
    private String sponsorId;
    @SerializedName("sponsor_name")
    @Expose
    private String sponsorName;
    @SerializedName("member_active")
    @Expose
    private String memberActive;
    @SerializedName("basic_active")
    @Expose
    private String basicActive;
    @SerializedName("MemberbronzeActive")
    @Expose
    private String memberbronzeActive;
    @SerializedName("vacant")
    @Expose
    private String vacant;
    @SerializedName("gold1")
    @Expose
    private String gold1;

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(String leftCount) {
        this.leftCount = leftCount;
    }

    public String getRightCount() {
        return rightCount;
    }

    public void setRightCount(String rightCount) {
        this.rightCount = rightCount;
    }

    public String getLeftActive() {
        return leftActive;
    }

    public void setLeftActive(String leftActive) {
        this.leftActive = leftActive;
    }

    public String getRightActive() {
        return rightActive;
    }

    public void setRightActive(String rightActive) {
        this.rightActive = rightActive;
    }

    public String getLeftPv() {
        return leftPv;
    }

    public void setLeftPv(String leftPv) {
        this.leftPv = leftPv;
    }

    public String getRightPv() {
        return rightPv;
    }

    public void setRightPv(String rightPv) {
        this.rightPv = rightPv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getMemberActive() {
        return memberActive;
    }

    public void setMemberActive(String memberActive) {
        this.memberActive = memberActive;
    }

    public String getBasicActive() {
        return basicActive;
    }

    public void setBasicActive(String basicActive) {
        this.basicActive = basicActive;
    }

    public String getMemberbronzeActive() {
        return memberbronzeActive;
    }

    public void setMemberbronzeActive(String memberbronzeActive) {
        this.memberbronzeActive = memberbronzeActive;
    }

    public String getVacant() {
        return vacant;
    }

    public void setVacant(String vacant) {
        this.vacant = vacant;
    }

    public String getGold1() {
        return gold1;
    }

    public void setGold1(String gold1) {
        this.gold1 = gold1;
    }
}
