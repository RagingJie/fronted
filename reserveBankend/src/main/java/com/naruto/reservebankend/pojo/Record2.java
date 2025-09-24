package com.naruto.reservebankend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Record2 {

    // 预约人
    private String ekcmdfazkbhm;
    // 预约时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date wx_reserve_date;
    private String wx_reserve_name;
    private String wx_reserve_id;

    public Record2() {
    }

    public Record2(String ekcmdfazkbhm, Date wx_reserve_date, String wx_reserve_name, String wx_reserve_id) {
        this.ekcmdfazkbhm = ekcmdfazkbhm;
        this.wx_reserve_date = wx_reserve_date;
        this.wx_reserve_name = wx_reserve_name;
        this.wx_reserve_id = wx_reserve_id;
    }

    public String getEkcmdfazkbhm() {
        return ekcmdfazkbhm;
    }

    public void setEkcmdfazkbhm(String ekcmdfazkbhm) {
        this.ekcmdfazkbhm = ekcmdfazkbhm;
    }

    public Date getWx_reserve_date() {
        return wx_reserve_date;
    }

    public void setWx_reserve_date(Date wx_reserve_date) {
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
