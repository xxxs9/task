package com.gameloft9.demo.dto.index;

import lombok.Data;

/**
 * 新闻返回实体
 */
@Data
public class NewsDto {

    private String id;
    /**
     * 文章标题
     */
    private String name;
    /**
     * 文章URL
     */
    private String address;
    /**
     * 评论数
     */
    private String businessHours;
    /**
     * 列表封面图片
     */
    private String images;
    /**
     * 文章内容
     */
    private String article;
    /**
     * 文章缩略图
     */
    private String image;

}
