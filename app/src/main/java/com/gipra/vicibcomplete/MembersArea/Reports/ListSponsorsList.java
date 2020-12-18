package com.gipra.vicibcomplete.MembersArea.Reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListSponsorsList {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("childusername")
    @Expose
    private String childusername;
    @SerializedName("dateofjoin")
    @Expose
    private String dateofjoin;
    @SerializedName("n_coupon_pv")
    @Expose
    private Integer nCouponPv;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public Integer getNCouponPv() {
        return nCouponPv;
    }

    public void setNCouponPv(Integer nCouponPv) {
        this.nCouponPv = nCouponPv;
    }

}
