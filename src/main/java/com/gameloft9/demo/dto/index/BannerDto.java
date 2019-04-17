package com.gameloft9.demo.dto.index;

import lombok.Data;

/**
 * 轮播图返回实体
 */
@Data
public class BannerDto {

    /**
     * 轮播图跳转链接
     */
    private String link;
    /**
     * 轮播图片地址
     */
    private String url;

}
