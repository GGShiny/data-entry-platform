package com.wenge.data.entry.enumertation;

import lombok.Getter;

/**
 * @Auther: 蔡武
 * @Date: 2019/12/26 11:48
 * @Description: 错误类型枚举类
 */
@Getter
public enum ErrorCodeEnum {

    // 系统错误
    UNKNOWN("500", "系统内部错误，请联系管理员"),
    PATH_NOT_FOUND("404", "路径不存在，请检查路径"),
    NO_AUTH("403", "没有权限，请联系管理员");

    ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;
}
