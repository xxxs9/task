package com.gameloft9.demo.dataaccess.model.user;

import javax.persistence.*;

/**
 * 英雄周免数据
 */
@Table(name = "hero_week_free")
public class HeroWeekFree {

    @Id
    private Integer id;

    /**
     * 英雄称号
     */
    @Column(name = "hero_title")
    private String heroTitle;

    /**
     * 英雄名称
     */
    @Column(name = "hero_name")
    private String heroName;

    /**
     * 图片
     */
    private String img;

    /**
     * 秒速
     */
    private String des;

    /**
     * @return id
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
     * @return hero_title
     */
    public String getHeroTitle() {
        return heroTitle;
    }

    /**
     * @param heroTitle
     */
    public void setHeroTitle(String heroTitle) {
        this.heroTitle = heroTitle;
    }

    /**
     * @return hero_name
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
     * @return img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return des
     */
    public String getDes() {
        return des;
    }

    /**
     * @param des
     */
    public void setDes(String des) {
        this.des = des;
    }
}