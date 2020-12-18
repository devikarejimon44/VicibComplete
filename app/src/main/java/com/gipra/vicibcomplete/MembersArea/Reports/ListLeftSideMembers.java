package com.gipra.vicibcomplete.MembersArea.Reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListLeftSideMembers {
    @SerializedName("count1")
    @Expose
    private Integer count1;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("childusername")
    @Expose
    private String childusername;
    @SerializedName("dateofjoin")
    @Expose
    private String dateofjoin;
    @SerializedName("actvatedddate")
    @Expose
    private Object actvatedddate;
    @SerializedName("n_coupon_pv")
    @Expose
    private String nCouponPv;
    @SerializedName("repurchase_pv")
    @Expose
    private Integer repurchasePv;

    public Integer getCount1() {
        return count1;
    }

    public void setCount1(Integer count1) {
        this.count1 = count1;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getChildusername() {
        return childusername;
    }

    public void setChildusername(String childusername) {
        this.childusername = childusername;
    }

    public String getDateofjoin() {
        return dateofjoin;
    }

    public void setDateofjoin(String dateofjoin) {
        this.dateofjoin = dateofjoin;
    }

    public Object getActvatedddate() {
        return actvatedddate;
    }

    public void setActvatedddate(Object actvatedddate) {
        this.actvatedddate = actvatedddate;
    }

    public String getNCouponPv() {
        return nCouponPv;
    }

    public void setNCouponPv(String nCouponPv) {
        this.nCouponPv = nCouponPv;
    }

    public Integer getRepurchasePv() {
        return repurchasePv;
    }

    public void setRepurchasePv(Integer repurchasePv) {
        this.repurchasePv = repurchasePv;
    }

}
