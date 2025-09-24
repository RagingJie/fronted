package com.naruto.reservebankend.pojo;

public class ResponseVo {

    // 预约日期
    private String reserveDate;

    // 时段
    private String timePeriod;

    // 预约结果，0-预约失败，1-预约成功
    private Integer reserveResult;

    public ResponseVo(String reserveDate, String timePeriod, Integer reserveResult) {
        this.reserveDate = reserveDate;
        this.timePeriod = timePeriod;
        this.reserveResult = reserveResult;
    }

    public ResponseVo() {
    }

    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Integer getReserveResult() {
        return reserveResult;
    }

    public void setReserveResult(Integer reserveResult) {
        this.reserveResult = reserveResult;
    }
}
