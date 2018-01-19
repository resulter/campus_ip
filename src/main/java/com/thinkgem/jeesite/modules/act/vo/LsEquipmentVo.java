package com.thinkgem.jeesite.modules.act.vo;

public class LsEquipmentVo {
    private Integer eId;//设备id

    private String iId;//ip的id

    private String officeName;//校区名

    private String oId;//校区ID

    private String address;//网络地址段

    private String minAddress;

    private String maxAddress;

    private String mask;//掩码

    private String nId;//网段id

    private String ip;//ip

    private String ipTag;//ip标识 0为使用，1已使用

    private String dId;//部门id

    private String department;

    private String equipmentName;

    private String location;

    private String username;

    private String password;

    private String remark;

    public LsEquipmentVo() {
    }

    public LsEquipmentVo(Integer eId, String iId, String officeName, String address, String minAddress, String maxAddress, String mask, String ip, String ipTag, String dId, String department, String equipmentName, String location, String username, String password, String remark) {
        this.eId = eId;
        this.iId = iId;
        this.officeName = officeName;
        this.address = address;
        this.minAddress = minAddress;
        this.maxAddress = maxAddress;
        this.mask = mask;
        this.ip = ip;
        this.ipTag = ipTag;
        this.dId = dId;
        this.department = department;
        this.equipmentName = equipmentName;
        this.location = location;
        this.username = username;
        this.password = password;
        this.remark = remark;
    }

    public LsEquipmentVo(Integer eId, String iId, String officeName, String address, String mask, String ip, String ipTag, String dId, String department, String equipmentName, String location, String username, String password, String remark) {
        this.eId = eId;
        this.iId = iId;
        this.officeName = officeName;
        this.address = address;
        this.mask = mask;
        this.ip = ip;
        this.ipTag = ipTag;
        this.dId = dId;
        this.department = department;
        this.equipmentName = equipmentName;
        this.location = location;
        this.username = username;
        this.password = password;
        this.remark = remark;
    }

    public LsEquipmentVo(String iId, String officeName, String address, String mask, String ip, String ipTag, String dId, String department, String equipmentName, String location, String username, String password, String remark) {

        this.iId = iId;
        this.officeName = officeName;
        this.address = address;
        this.mask = mask;
        this.ip = ip;
        this.ipTag = ipTag;
        this.dId = dId;
        this.department = department;
        this.equipmentName = equipmentName;
        this.location = location;
        this.username = username;
        this.password = password;
        this.remark = remark;
    }

    public LsEquipmentVo(Integer eId, String iId, String officeName, String oId, String address, String minAddress, String maxAddress, String mask, String nId, String ip, String ipTag, String dId, String department, String equipmentName, String location, String username, String password, String remark) {
        this.eId = eId;
        this.iId = iId;
        this.officeName = officeName;
        this.oId = oId;
        this.address = address;
        this.minAddress = minAddress;
        this.maxAddress = maxAddress;
        this.mask = mask;
        this.nId = nId;
        this.ip = ip;
        this.ipTag = ipTag;
        this.dId = dId;
        this.department = department;
        this.equipmentName = equipmentName;
        this.location = location;
        this.username = username;
        this.password = password;
        this.remark = remark;
    }

    public String getMinAddress() {
        return minAddress;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId;
    }

    public void setMinAddress(String minAddress) {
        this.minAddress = minAddress;
    }

    public String getMaxAddress() {
        return maxAddress;
    }

    public void setMaxAddress(String maxAddress) {
        this.maxAddress = maxAddress;
    }

    @Override
    public String toString() {
        return "LsEquipmentVo{" +
                "eId=" + eId +
                ", iId='" + iId + '\'' +
                ", officeName='" + officeName + '\'' +
                ", oId='" + oId + '\'' +
                ", address='" + address + '\'' +
                ", minAddress='" + minAddress + '\'' +
                ", maxAddress='" + maxAddress + '\'' +
                ", mask='" + mask + '\'' +
                ", nId='" + nId + '\'' +
                ", ip='" + ip + '\'' +
                ", ipTag='" + ipTag + '\'' +
                ", dId='" + dId + '\'' +
                ", department='" + department + '\'' +
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

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpTag() {
        return ipTag;
    }

    public void setIpTag(String ipTag) {
        this.ipTag = ipTag;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}