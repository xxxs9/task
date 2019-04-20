package com.gameloft9.demo.dataaccess.model.user;

import javax.persistence.*;

/**
 * 英雄基本表
 */
@Table(name = "hero_base")
public class HeroBase {
    @Column(name = "ID")
    @Id
    private Integer id;

    /**
     * 英雄名称
     */
    @Column(name = "HERO_NAME")
    private String heroName;

    /**
     * 英雄称号
     */
    @Column(name = "HERO_TITLE")
    private String heroTitle;

    /**
     * 英雄头像URL
     */
    @Column(name = "IMG_URl")
    private String imgUrl;

    /**
     * 英雄详情的爬取路径
     */
    @Column(name = "DETAIL_URL")
    private String detailUrl;

    /**
     * 英雄描述
     */
    @Column(name = "DES")
    private String des;

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
     * @return HERO_TITLE
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
     * @return IMG_URl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @return DETAIL_URL
     */
    public String getDetailUrl() {
        return detailUrl;
    }

    /**
     * @param detailUrl
     */
    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    /**
     * @return DES
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