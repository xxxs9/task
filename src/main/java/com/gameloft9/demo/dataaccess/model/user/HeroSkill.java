package com.gameloft9.demo.dataaccess.model.user;

import javax.persistence.*;

/**
 * 英雄技能实体
 */
@Table(name = "hero_skill")
public class HeroSkill {
    @Column(name = "ID")
    @Id
    private Integer id;

    /**
     * 英雄名与英雄明细对应
     * hero_detail表的hero_name
     */
    @Column(name = "HERO_NAME")
    private String heroName;

    /**
     * 技能图标Url
     */
    @Column(name = "SKILL_IMG_URL")
    private String skillImgUrl;

    /**
     * 技能名称
     */
    @Column(name = "SKILL_NAME")
    private String skillName;

    /**
     * 快捷键
     */
    @Column(name = "SKILL_KEY")
    private String skillKey;

    /**
     * 技能描述
     */
    @Column(name = "SKILL_DESCRIBE")
    private String skillDescribe;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return HERO_NAME
     */
    public String getHeroName() {
        return heroName;
    }

    /**
     * @param heroName
     */
    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    /**
     * @return SKILL_IMG_URL
     */
    public String getSkillImgUrl() {
        return skillImgUrl;
    }

    /**
     * @param skillImgUrl
     */
    public void setSkillImgUrl(String skillImgUrl) {
        this.skillImgUrl = skillImgUrl;
    }

    /**
     * @return SKILL_NAME
     */
    public String getSkillName() {
        return skillName;
    }

    /**
     * @param skillName
     */
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    /**
     * @return SKILL_KEY
     */
    public String getSkillKey() {
        return skillKey;
    }

    /**
     * @param skillKey
     */
    public void setSkillKey(String skillKey) {
        this.skillKey = skillKey;
    }

    /**
     * @return SKILL_DESCRIBE
     */
    public String getSkillDescribe() {
        return skillDescribe;
    }

    /**
     * @param skillDescribe
     */
    public void setSkillDescribe(String skillDescribe) {
        this.skillDescribe = skillDescribe;
    }
}