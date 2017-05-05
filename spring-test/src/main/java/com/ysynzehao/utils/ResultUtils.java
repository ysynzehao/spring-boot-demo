package com.ysynzehao.utils;

import com.ysynzehao.entry.Result;

/**
 * Created by Administrator on 2017/5/4.
 */
public class ResultUtils {

    /**
     *
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(StatusCode.Success.getValue());
        result.setMessage(StatusCode.Success.getMessage());
        result.setObject(object);
        return result;
    }

    /**
     *
     * @return
     */
    public static Result fail() {
        Result result = new Result();
        result.setCode(StatusCode.Fail.getValue());
        result.setMessage(StatusCode.Fail.getMessage());
        return result;
    }

    /**
     *
     * @param statusCode
     * @param object
     * @return
     */
    public static Result getResult(StatusCode statusCode, Object object) {
        Result result = new Result();
        result.setCode(statusCode.getValue());
        result.setMessage(statusCode.getMessage());
        result.setObject(object);
        return result;
    }
}
