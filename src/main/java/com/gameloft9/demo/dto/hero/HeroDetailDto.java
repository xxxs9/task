package com.gameloft9.demo.dto.hero;

import lombok.Data;

import java.util.List;

/**
 * 英雄详情返回实体
 */
@Data
public class HeroDetailDto {

    /**
     * 英雄名称
     */
    private String names;
    /**
     * 定位，职业
     */
    private String col;

    /**
     *  属性
     *  攻
     *  法
     *  防
     *  操作难度
     */
    private List<String> conut;
    /**
     * 蓝色精粹和点券
     */
    private String num;
    /**
     * 定位：adc
     */
    private String output;
    /**
     * 定位比例
     */
    private String percent;
    /**
     * 胜率排名
     */
    private String coun;
    /**
     * 登场排名
     */
    private String nu;
    /**
     * KDN排名
     */
    private String ranking;
    /**
     * 技能
     */
    private List<HeroSkillDto> skill;


}
