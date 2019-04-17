package com.gameloft9.demo.dto.hero;

import lombok.Data;

/**
 * 英雄列表实体
 */
@Data
public class HeroDto {

    /**
     * 英雄称号
     */
    private String title;
    /**
     * 英雄名
     */
    private String names;
    /**
     * 英雄职业
     */
    private String type;
}
