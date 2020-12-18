package com.gipra.vicibcomplete.MembersArea.Complaints;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListComplaintList {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("d_date")
    @Expose
    private String dDate;
    @SerializedName("c_complaint_category")
    @Expose
    private String cComplaintCategory;
    @SerializedName("c_priority")
    @Expose
    private String cPriority;
    @SerializedName("c_subject")
    @Expose
    private String cSubject;
    @SerializedName("c_description")
    @Expose
    private String cDescription;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDDate() {
        return dDate;
    }

    public void setDDate(String dDate) {
        this.dDate = dDate;
    }

    public String getCComplaintCategory() {
        return cComplaintCategory;
    }

    public void setCComplaintCategory(String cComplaintCategory) {
        this.cComplaintCategory = cComplaintCategory;
    }

    public String getCPriority() {
        return cPriority;
    }

    public void setCPriority(String cPriority) {
        this.cPriority = cPriority;
    }

    public String getCSubject() {
        return cSubject;
    }

    public void setCSubject(String cSubject) {
        this.cSubject = cSubject;
    }

    public String getCDescription() {
        return cDescription;
    }

    public void setCDescription(String cDescription) {
        this.cDescription = cDescription;
    }


}
