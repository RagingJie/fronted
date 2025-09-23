package com.naruto.reservebankend.enums;

public enum HttpRequestResultEnum {

    SUCCESS(200, "SUCCESS", "请求成功"),

    FAIL(400, "FAIL", "请求失败"),

    ERROR(500, "ERROR", "服务器异常");

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态值
     */
    private String value;
    /**
     * 描述
     */
    private String description;

    HttpRequestResultEnum(Integer code, String value, String description) {
        this.code = code;
        this.value = value;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
