package com.thinkgem.jeesite.modules.act.entity;

public class LsOffice {
    private Integer oId;

    private String oName;

    private String dIds;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName == null ? null : oName.trim();
    }

    public String getdIds() {
        return dIds;
    }

    public void setdIds(String dIds) {
        this.dIds = dIds == null ? null : dIds.trim();
    }

    public LsOffice() {
    }

    public LsOffice(Integer oId, String oName, String dIds) {
        this.oId = oId;
        this.oName = oName;
        this.dIds = dIds;
    }

    public LsOffice(String oName, String dIds) {
        this.oName = oName;
        this.dIds = dIds;
    }

    @Override
    public String toString() {
        return "LsOffice{" +
                "oId=" + oId +
                ", oName='" + oName + '\'' +
                ", dIds='" + dIds + '\'' +
                '}';
    }
}