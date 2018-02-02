package com.thinkgem.jeesite.modules.act.entity;

import com.thinkgem.jeesite.common.utils.StringUtils;

public class SearchMapNew {
    String officeId;
    String search_equipment;
    String search_address;
    String search_ip;
    String pre;

    public SearchMapNew(String officeId, String search_equipment, String search_address, String search_ip) {
        this.officeId = officeId;
        this.search_equipment = search_equipment;
        this.search_address = search_address;
        this.search_ip = search_ip;
    }


    public SearchMapNew() {
    }

    @Override
    public String toString() {
        return "SearchMapNew{" +
                "officeId='" + officeId + '\'' +
                ", search_equipment='" + search_equipment + '\'' +
                ", search_address='" + search_address + '\'' +
                ", search_ip='" + search_ip + '\'' +
                '}';
    }

    public String getPre() {
        return pre;
    }

    public void setPre() {
        String s = this.search_address;
        String [] a = s.split(" - ");
        System.out.println("啊啊         " + StringUtils.substringBeforeLast(a[0],"."));
        this.pre = StringUtils.substringBeforeLast(a[0],".");
    }

    public String getSearch_equipment() {
        return search_equipment;
    }

    public void setSearch_equipment(String search_equipment) {
        this.search_equipment = search_equipment;
    }

    public String getSearch_address() {
        return search_address;
    }

    public void setSearch_address(String search_address) {
        this.search_address = search_address;
    }

    public String getSearch_ip() {
        return search_ip;
    }

    public void setSearch_ip(String search_ip) {
        this.search_ip = search_ip;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }
}
