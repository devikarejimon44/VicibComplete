package com.gipra.vicibcomplete.MembersArea.StandardPlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Standard_ListLeftSideSales {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("childusername")
    @Expose
    private String childusername;
    @SerializedName("dactivated")
    @Expose
    private String dactivated;
    @SerializedName("N_PV")
    @Expose
    private String nPV;
    @SerializedName("c_status")
    @Expose
    private String cStatus;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChildusername() {
        return childusername;
    }

    public void setChildusername(String childusername) {
        this.childusername = childusername;
    }

    public String getDactivated() {
        return dactivated;
    }

    public void setDactivated(String dactivated) {
        this.dactivated = dactivated;
    }

    public String getNPV() {
        return nPV;
    }

    public void setNPV(String nPV) {
        this.nPV = nPV;
    }

    public String getCStatus() {
        return cStatus;
    }

    public void setCStatus(String cStatus) {
        this.cStatus = cStatus;
    }

}
