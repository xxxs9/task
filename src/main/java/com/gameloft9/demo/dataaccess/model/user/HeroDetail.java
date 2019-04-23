package com.gameloft9.demo.dataaccess.model.user;

import javax.persistence.*;

/**
 * 英雄详情
 */
@Table(name = "hero_detail")
public class HeroDetail {

    @Column(name = "ID")
    @Id
    private Integer id;

    /**
     * 英雄名称
     */
    @Column(name = "HERO_NAME")
    private String heroName;

    /**
     * 英雄视频路径
     */
    @Column(name = "VIDEO_URL")
    private String videoUrl;

    /**
     * 定位
     */
    @Column(name = "OUTPUT")
    private String output;

    /**
     * 生存能力
     */
    @Column(name = "EXISTENCE")
    private Integer existence;

    /**
     * 物理攻击
     */
    @Column(name = "PHYSICS")
    private Integer physics;

    /**
     * 魔法攻击
     */
    @Column(name = "MAGIC")
    private Integer magic;

    /**
     * 金币
     */
    @Column(name = "GOLD_COIN")
    private String goldCoin;
    /**
     * 点券
     */
    @Column(name = "TICKET")
    private String ticket;


    /**
     * 操作难度
     */
    @Column(name = "OPERATION")
    private Integer operation;

    /**
     * 英雄称号
     */
    @Column(name = "HERO_TITLE")
    private String heroTitle;

    /**
     * 英雄背景图片
     */
    @Column(name = "BACKGROUND_IMG")
    private String backgroundImg;

    /**
     * 英雄装备ID
     */
    @Column(name = "EQUIP_ID")
    private String equipId;

    /**
     * 符文图片
     */
    @Column(name = "RUNE_IMG")
    private String runeImg;
    /**
     * 符文描述
     */
    @Column(name = "RUNE_DES")
    private String runeDes;

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
     * @return VIDEO_URL
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * @param videoUrl
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * @return OUTPUT
     */
    public String getOutput() {
        return output;
    }

    /**
     * @param output
     */
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * @return EXISTENCE
     */
    public Integer getExistence() {
        return existence;
    }

    /**
     * @param existence
     */
    public void setExistence(Integer existence) {
        this.existence = existence;
    }

    /**
     * @return PHYSICS
     */
    public Integer getPhysics() {
        return physics;
    }

    /**
     * @param physics
     */
    public void setPhysics(Integer physics) {
        this.physics = physics;
    }

    /**
     * @return MAGIC
     */
    public Integer getMagic() {
        return magic;
    }

    /**
     * @param magic
     */
    public void setMagic(Integer magic) {
        this.magic = magic;
    }

    /**
     * @return OPERATION
     */
    public Integer getOperation() {
        return operation;
    }

    /**
     * @param operation
     */
    public void setOperation(Integer operation) {
        this.operation = operation;
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

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public String getGoldCoin() {
        return goldCoin;
    }

    public void setGoldCoin(String goldCoin) {
        this.goldCoin = goldCoin;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }

    public String getRuneImg() {
        return runeImg;
    }

    public void setRuneImg(String runeImg) {
        this.runeImg = runeImg;
    }

    public String getRuneDes() {
        return runeDes;
    }

    public void setRuneDes(String runeDes) {
        this.runeDes = runeDes;
    }
}