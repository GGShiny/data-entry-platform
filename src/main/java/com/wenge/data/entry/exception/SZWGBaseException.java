package com.wenge.data.entry.exception;

import com.wenge.data.entry.enumertation.ErrorCodeEnum;
import lombok.Getter;

/**
 * @Auther: 蔡武
 * @Date: 2019/12/26 11:48
 * @Description: 基础异常
 */
@Getter
public class SZWGBaseException extends RuntimeException {

    /**
     * 异常状态码
     */
    private String code;

    /**
     * 异常消息
     */
    private String message;

    public SZWGBaseException(ErrorCodeEnum errorCodeEnum) {
        this.code = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMsg();
    }
}
