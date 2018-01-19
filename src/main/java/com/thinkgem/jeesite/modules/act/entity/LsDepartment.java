package com.thinkgem.jeesite.modules.act.entity;

public class LsDepartment {
    private Integer dId;

    private String dName;

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName == null ? null : dName.trim();
    }

    public LsDepartment() {
    }

    public LsDepartment(Integer dId, String dName) {
        this.dId = dId;
        this.dName = dName;
    }

    public LsDepartment(String dName) {
        this.dName = dName;
    }

    @Override
    public String toString() {
        return "LsDepartment{" +
                "dId=" + dId +
                ", dName='" + dName + '\'' +
                '}';
    }
}