package com.gipra.vicibcomplete.MembersArea.ui.Genealogy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class SponsortreeList {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("C_USERNAME")
    @Expose
    private String cUSERNAME;
    @SerializedName("C_FNAME")
    @Expose
    private String cFNAME;
    @SerializedName("PN_ID")
    @Expose
    private String pNID;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCUSERNAME() {
        return cUSERNAME;
    }

    public void setCUSERNAME(String cUSERNAME) {
        this.cUSERNAME = cUSERNAME;
    }

    public String getCFNAME() {
        return cFNAME;
    }

    public void setCFNAME(String cFNAME) {
        this.cFNAME = cFNAME;
    }

    public String getPNID() {
        return pNID;
    }

    public void setPNID(String pNID) {
        this.pNID = pNID;
    }



}
