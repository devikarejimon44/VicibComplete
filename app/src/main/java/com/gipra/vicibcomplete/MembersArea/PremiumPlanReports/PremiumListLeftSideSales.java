package com.gipra.vicibcomplete.MembersArea.PremiumPlanReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PremiumListLeftSideSales {
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

}
