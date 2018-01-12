package com.thinkgem.jeesite.modules.act.entity;

public class BaseDataSearchMap {
    String search_campus;
    String search_address;
    String search_ip;

    public BaseDataSearchMap(String search_campus, String search_address, String search_ip) {
        this.search_campus = search_campus;
        this.search_address = search_address;
        this.search_ip = search_ip;
    }

    public BaseDataSearchMap() {
    }

    public String getSearch_campus() {
        return search_campus;
    }

    public void setSearch_campus(String search_campus) {
        this.search_campus = search_campus;
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

    @Override
    public String toString() {
        return "BaseDataSearchMap{" +
                "search_campus='" + search_campus + '\'' +
                ", search_address='" + search_address + '\'' +
                ", search_ip='" + search_ip + '\'' +
                '}';
    }
}
