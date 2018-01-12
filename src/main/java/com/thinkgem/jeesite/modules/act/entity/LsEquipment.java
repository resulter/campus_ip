package com.thinkgem.jeesite.modules.act.entity;

public class LsEquipment {
    private Integer eId;

    private String iId;

    private String dId;

    private String equipmentName;

    private String location;

    private String username;

    private String password;

    private String remark;

    public LsEquipment() {
    }

    public LsEquipment(String iId, String dId, String equipmentName, String location, String username, String password, String remark) {
        this.iId = iId;
        this.dId = dId;
        this.equipmentName = equipmentName;
        this.location = location;
        this.username = username;
        this.password = password;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "LsEquipment{" +
                "eId=" + eId +
                ", iId='" + iId + '\'' +
                ", dId='" + dId + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", location='" + location + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String getiId() {
        return iId;
    }

    public void setiId(String iId) {
        this.iId = iId == null ? null : iId.trim();
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId == null ? null : dId.trim();
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName == null ? null : equipmentName.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}