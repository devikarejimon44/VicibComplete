package com.gipra.vicibcomplete.MembersArea.Reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListBasicActiveMembers {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("joiningdatedate")
    @Expose
    private String joiningdatedate;
    @SerializedName("actvatedddate")
    @Expose
    private String actvatedddate;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJoiningdatedate() {
        return joiningdatedate;
    }

    public void setJoiningdatedate(String joiningdatedate) {
        this.joiningdatedate = joiningdatedate;
    }

    public String getActvatedddate() {
        return actvatedddate;
    }

    public void setActvatedddate(String actvatedddate) {
        this.actvatedddate = actvatedddate;
    }
}
