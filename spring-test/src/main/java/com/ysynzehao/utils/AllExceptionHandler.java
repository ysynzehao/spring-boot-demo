package com.ysynzehao.utils;

import com.ysynzehao.entry.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/5/4.
 */
@ControllerAdvice
@ResponseBody
public class AllExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return ResultUtils.getResult(StatusCode.Exception, e.getMessage());
    }
}
