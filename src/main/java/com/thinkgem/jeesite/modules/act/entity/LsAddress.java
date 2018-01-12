package com.thinkgem.jeesite.modules.act.entity;

public class LsAddress {
    private Integer nId;

    private String oId;

    private String nMinAddress;

    private String nMaxAddress;

    private String mask;

    public Integer getnId() {
        return nId;
    }

    public void setnId(Integer nId) {
        this.nId = nId;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId == null ? null : oId.trim();
    }

    public String getnMinAddress() {
        return nMinAddress;
    }

    public void setnMinAddress(String nMinAddress) {
        this.nMinAddress = nMinAddress == null ? null : nMinAddress.trim();
    }

    public String getnMaxAddress() {
        return nMaxAddress;
    }

    public void setnMaxAddress(String nMaxAddress) {
        this.nMaxAddress = nMaxAddress == null ? null : nMaxAddress.trim();
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask == null ? null : mask.trim();
    }

    @Override
    public String toString() {
        return "LsAddress{" +
                "nId=" + nId +
                ", oId='" + oId + '\'' +
                ", nMinAddress='" + nMinAddress + '\'' +
                ", nMaxAddress='" + nMaxAddress + '\'' +
                ", mask='" + mask + '\'' +
                '}';
    }

    public LsAddress() {
    }

    public LsAddress(String oId, String nMinAddress, String nMaxAddress, String mask) {
        this.oId = oId;
        this.nMinAddress = nMinAddress;
        this.nMaxAddress = nMaxAddress;
        this.mask = mask;
    }

    public LsAddress(Integer nId, String oId, String nMinAddress, String nMaxAddress, String mask) {
        this.nId = nId;
        this.oId = oId;
        this.nMinAddress = nMinAddress;
        this.nMaxAddress = nMaxAddress;
        this.mask = mask;
    }
}