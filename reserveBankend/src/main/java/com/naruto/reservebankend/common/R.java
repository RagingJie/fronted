package com.naruto.reservebankend.common;

import com.naruto.reservebankend.enums.HttpRequestResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {

    private static final long serialVersionUID = 32423534589348L;
    // 成功
    private static final Integer SUCCESS_CODE = HttpRequestResultEnum.SUCCESS.getCode();
    private static final String SUCCESS_MSG = HttpRequestResultEnum.SUCCESS.getValue();
    // 异常
    private static final Integer ERROR_CODE = HttpRequestResultEnum.ERROR.getCode();
    private static final String ERROR_MSG = HttpRequestResultEnum.ERROR.getValue();
    // 失败
    private static final Integer FAIL_CODE = HttpRequestResultEnum.FAIL.getCode();
    private static final String FAIL_MSG = HttpRequestResultEnum.FAIL.getValue();


    private Integer code;
    private String message;
    private T data;

    public static <T> R<T> success() {
        return new R<T>(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    public static <T> R<T> success(String msg) {
        return new R<T>(SUCCESS_CODE, msg, null);
    }

    public static <T> R<T> success(T data) {
        return new R<T>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> R<T> success(String msg, T data) {
        return new R<T>(SUCCESS_CODE, msg, data);
    }

    public static <T> R<T> success(Integer code, String msg, T data) {
        return new R<T>(code, msg, data);
    }

    public static <T> R<T> fail() {
        return new R<T>(FAIL_CODE, FAIL_MSG, null);
    }

    public static <T> R<T> fail(String msg) {
        return new R<T>(FAIL_CODE, msg, null);
    }

    public static <T> R<T> fail(T data) {
        return new R<T>(FAIL_CODE, FAIL_MSG, data);
    }

    public static <T> R<T> fail(String msg, T data) {
        return new R<T>(FAIL_CODE, msg, data);
    }

    public static <T> R<T> fail(Integer code, String msg, T data) {
        return new R<T>(code, msg, data);
    }

    public static <T> R<T> error() {
        return new R<T>(ERROR_CODE, ERROR_MSG, null);
    }

    public static <T> R<T> error(String msg) {
        return new R<T>(ERROR_CODE, msg, null);
    }

    public static <T> R<T> error(T data) {
        return new R<T>(ERROR_CODE, ERROR_MSG, data);
    }

    public static <T> R<T> error(String msg, T data) {
        return new R<T>(ERROR_CODE, msg, data);
    }

    public static <T> R<T> error(Integer code, String msg, T data) {
        return new R<T>(code, msg, data);
    }

    public static <T> R<T> error(Integer code, String msg) {
        return new R<T>(code, msg, null);
    }
}
