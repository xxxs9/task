package com.gameloft9.demo.dto.hero;

import lombok.Data;

/**
 * 英雄技能
 */
@Data
public class HeroSkillDto {

    /**
     * 技能名称
     */
    private String name;
    /**
     * 技能是主动还是被动
     */
    private String na;
    /**
     * 技能描述
     */
    private String title;
    /**
     * 技能视频
     */
    private String url;
}
