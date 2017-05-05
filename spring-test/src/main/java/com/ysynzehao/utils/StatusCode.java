package com.ysynzehao.utils;

/**
 * Created by Administrator on 2017/5/4.
 */
public enum StatusCode {

    Exception(-1, "系统错误"),

    Success(0, "成功"),

    Fail(1, "失败");

    private int value;

    private String message;

    StatusCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
