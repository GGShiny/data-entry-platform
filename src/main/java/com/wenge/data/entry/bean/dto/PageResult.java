package com.wenge.data.entry.bean.dto;

import lombok.Data;

import java.util.List;

/**
 * @Auther: 蔡武
 * @Date: 2019/12/30 17:33
 * @Description: 分页查询结果封装类
 */
@Data
public class PageResult {

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 记录总数
     */
    private long totalSize;

    /**
     * 页码总数
     */
    private int totalPages;

    /**
     * 数据模型
     */
    private List<?> content;

}
