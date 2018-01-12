package com.thinkgem.jeesite.modules.act.entity;

public class LsIps {
    private Integer id;

    private Integer officeId;

    private String networkAddress;

    private String mask;

    private String ip;

    private String tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(String networkAddress) {
        this.networkAddress = networkAddress == null ? null : networkAddress.trim();
    }

    @Override
    public String toString() {
        return "LsIps{" +
                "id=" + id +
                ", officeId=" + officeId +
                ", networkAddress='" + networkAddress + '\'' +
                ", mask='" + mask + '\'' +
                ", ip='" + ip + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    public LsIps() {
    }

    public LsIps(Integer officeId, String networkAddress, String mask, String ip, String tag) {
        this.officeId = officeId;
        this.networkAddress = networkAddress;
        this.mask = mask;
        this.ip = ip;
        this.tag = tag;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }
}