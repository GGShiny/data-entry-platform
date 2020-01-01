package com.wenge.data.entry.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 返回结果封装类
 * @author: 王春海
 * @date: 2019/4/10 13:13
 */
@Data
@ApiModel
public class ResultDto<T> {

    /**
     * 返回请求结果的状态
     */
    @ApiModelProperty(value="响应状态码",dataType="String",name="code",example="200")
    private String code;

    /**
     * 返回请求结果的message
     */
    @ApiModelProperty(value="响应消息",dataType="String",name="message",position = 1,example="请求成功")
    private String message;

    /**
     * 封装具体数据
     */
    @ApiModelProperty(value="响应结果",dataType="Object",name="data",position = 2)
    private T data;

    public ResultDto(){

    }

    public static ResultDto success(Object object){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode("200");
        resultDto.setMessage("请求成功");
        resultDto.setData(object);
        return resultDto;
    }
    public static ResultDto success(){
        return success(null);
    }

    public static ResultDto error(String code,String message){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }
}
