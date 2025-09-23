package com.naruto.reservebankend.pojo;

public class Record {

    // 预约人
    private String ekcmdfazkbhm;
    // 预约时间
    private String wx_reserve_date;
    private String wx_reserve_name;
    private String wx_reserve_id;

    public Record(String ekcmdfazkbhm, String wx_reserve_date, String wx_reserve_name, String wx_reserve_id) {
        this.ekcmdfazkbhm = ekcmdfazkbhm;
        this.wx_reserve_date = wx_reserve_date;
        this.wx_reserve_name = wx_reserve_name;
        this.wx_reserve_id = wx_reserve_id;
    }

    public Record() {
        this.ekcmdfazkbhm = "徐康";
    }

    public String getEkcmdfazkbhm() {
        return ekcmdfazkbhm;
    }

    public void setEkcmdfazkbhm(String ekcmdfazkbhm) {
        this.ekcmdfazkbhm = ekcmdfazkbhm;
    }

    public String getWx_reserve_date() {
        return wx_reserve_date;
    }

    public void setWx_reserve_date(String wx_reserve_date) {
        this.wx_reserve_date = wx_reserve_date;
    }

    public String getWx_reserve_name() {
        return wx_reserve_name;
    }

    public void setWx_reserve_name(String wx_reserve_name) {
        this.wx_reserve_name = wx_reserve_name;
    }

    public String getWx_reserve_id() {
        return wx_reserve_id;
    }

    public void setWx_reserve_id(String wx_reserve_id) {
        this.wx_reserve_id = wx_reserve_id;
    }
}
