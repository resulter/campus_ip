package com.thinkgem.jeesite.modules.act.entity;

public class BaseData {
    private Integer id;

    private String campusName;

    private String networkAddress;

    private String mask;

    private String ip;

    private String usingEquipment;

    private String usingDepartment;

    private String storagePosition;

    private String username;

    private String password;

    private String remarks;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "id=" + id +
                ", campusName='" + campusName + '\'' +
                ", networkAddress='" + networkAddress + '\'' +
                ", mask='" + mask + '\'' +
                ", ip='" + ip + '\'' +
                ", usingEquipment='" + usingEquipment + '\'' +
                ", usingDepartment='" + usingDepartment + '\'' +
                ", storagePosition='" + storagePosition + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public BaseData(String campusName, String networkAddress, String mask, String ip, String usingEquipment, String usingDepartment, String storagePosition, String username, String password, String remarks) {
        this.campusName = campusName;
        this.networkAddress = networkAddress;
        this.mask = mask;
        this.ip = ip;
        this.usingEquipment = usingEquipment;
        this.usingDepartment = usingDepartment;
        this.storagePosition = storagePosition;
        this.username = username;
        this.password = password;
        this.remarks = remarks;
    }

    public BaseData() {
    }

    public BaseData(Integer id, String campusName, String networkAddress, String mask, String ip, String usingEquipment, String usingDepartment, String storagePosition, String username, String password, String remarks) {
        this.id = id;
        this.campusName = campusName;
        this.networkAddress = networkAddress;
        this.mask = mask;
        this.ip = ip;
        this.usingEquipment = usingEquipment;
        this.usingDepartment = usingDepartment;
        this.storagePosition = storagePosition;
        this.username = username;
        this.password = password;
        this.remarks = remarks;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName == null ? null : campusName.trim();
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(String networkAddress) {
        this.networkAddress = networkAddress == null ? null : networkAddress.trim();
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask == null ? null : mask.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getUsingEquipment() {
        return usingEquipment;
    }

    public void setUsingEquipment(String usingEquipment) {
        this.usingEquipment = usingEquipment == null ? null : usingEquipment.trim();
    }

    public String getUsingDepartment() {
        return usingDepartment;
    }

    public void setUsingDepartment(String usingDepartment) {
        this.usingDepartment = usingDepartment == null ? null : usingDepartment.trim();
    }

    public String getStoragePosition() {
        return storagePosition;
    }

    public void setStoragePosition(String storagePosition) {
        this.storagePosition = storagePosition == null ? null : storagePosition.trim();
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}