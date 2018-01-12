package com.thinkgem.jeesite.modules.act.entity;

public class LsIp {
    private Integer iId;

    private String ip;

    private String nId;

    private String tag;

    public Integer getiId() {
        return iId;
    }

    public void setiId(Integer iId) {
        this.iId = iId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId == null ? null : nId.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public LsIp() {
    }

    public LsIp(String ip, String nId, String tag) {
        this.ip = ip;
        this.nId = nId;
        this.tag = tag;
    }

    public LsIp(Integer iId, String ip, String nId, String tag) {
        this.iId = iId;
        this.ip = ip;
        this.nId = nId;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "LsIp{" +
                "iId=" + iId +
                ", ip='" + ip + '\'' +
                ", nId='" + nId + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}