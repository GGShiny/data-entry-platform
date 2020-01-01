package com.wenge.data.entry.exception;

import com.wenge.data.entry.bean.dto.ResultDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auther: 蔡武
 * @Date: 2019/12/26 11:48
 * @Description: 全局异常拦截器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SZWGBaseException.class)
    public ResultDto SZWGBaseException(SZWGBaseException e) {
        String code = e.getCode();
        String msg = e.getMessage();
        return ResultDto.error(code, msg);
    }
}
