package com.naruto.reservebankend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class RequestVo {

    private List<String> reserveTimesList;
    private Date taskStartTime;
    private Date taskEndTime;
    private String wx_session_user_id;
    private String service_id;
    private String service_record_id;
    private Record2 service_record;
    private List<Object> child_service_records;
    private String code;

    public RequestVo() {
    }

    public RequestVo(List<String> reserveTimesList, Date taskStartTime, Date taskEndTime, String wx_session_user_id, String service_id, String service_record_id, Record2 service_record, List<Object> child_service_records, String code) {
        this.reserveTimesList = reserveTimesList;
        this.taskStartTime = taskStartTime;
        this.taskEndTime = taskEndTime;
        this.wx_session_user_id = wx_session_user_id;
        this.service_id = service_id;
        this.service_record_id = service_record_id;
        this.service_record = service_record;
        this.child_service_records = child_service_records;
        this.code = code;
    }

    public RequestVo(List<String> reserveTimesList, Date taskStartTime, String wx_session_user_id, String service_id, String service_record_id, Record2 service_record, List<Object> child_service_records, String code) {
        this.reserveTimesList = reserveTimesList;
        this.taskStartTime = taskStartTime;
        this.wx_session_user_id = wx_session_user_id;
        this.service_id = service_id;
        this.service_record_id = service_record_id;
        this.service_record = service_record;
        this.child_service_records = child_service_records;
        this.code = code;
    }

    public List<String> getReserveTimesList() {
        return reserveTimesList;
    }

    public void setReserveTimesList(List<String> reserveTimesList) {
        this.reserveTimesList = reserveTimesList;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public String getWx_session_user_id() {
        return wx_session_user_id;
    }

    public void setWx_session_user_id(String wx_session_user_id) {
        this.wx_session_user_id = wx_session_user_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_record_id() {
        return service_record_id;
    }

    public void setService_record_id(String service_record_id) {
        this.service_record_id = service_record_id;
    }

    public Record2 getService_record() {
        return service_record;
    }

    public void setService_record(Record2 service_record) {
        this.service_record = service_record;
    }

    public List<Object> getChild_service_records() {
        return child_service_records;
    }

    public void setChild_service_records(List<Object> child_service_records) {
        this.child_service_records = child_service_records;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }
}
