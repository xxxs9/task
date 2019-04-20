package com.gameloft9.demo.dataaccess.model.user;

import javax.persistence.*;

/**
 * 用户游戏基本信息
 */
@Table(name = "reptile_user_base")
public class ReptileUserBase {
    @Column(name = "ID")
    @Id
    private Integer id;

    /**
     * 爬取ID
     * 例如：23238298738
     */
    @Column(name = "REPTILE_ID")
    private String reptileId;
    /**
     * 服务器ID
     * 例如：dx1
     */
    @Column(name = "SERVER_ID")
    private String serverId;

    /**
     * 用户等级
     */
    @Column(name = "LEVEL")
    private String level;

    /**
     * 战斗力
     */
    @Column(name = "BATLE_ABILITY")
    private Integer batleAbility;

    /**
     * 赞扬数
     */
    @Column(name = "PRAISE")
    private Integer praise;

    /**
     * 贬低数
     */
    @Column(name = "BELTTLE")
    private Integer belttle;

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
     * @return REPTILE_ID
     */
    public String getReptileId() {
        return reptileId;
    }

    /**
     * @param reptileId
     */
    public void setReptileId(String reptileId) {
        this.reptileId = reptileId;
    }

    /**
     * @return SERVER_ID
     */
    public String getServerId() {
        return serverId;
    }

    /**
     * @param serverId
     */
    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    /**
     * @return LEVEL
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return BATLE_ABILITY
     */
    public Integer getBatleAbility() {
        return batleAbility;
    }

    /**
     * @param batleAbility
     */
    public void setBatleAbility(Integer batleAbility) {
        this.batleAbility = batleAbility;
    }

    /**
     * @return PRAISE
     */
    public Integer getPraise() {
        return praise;
    }

    /**
     * @param praise
     */
    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    /**
     * @return BELTTLE
     */
    public Integer getBelttle() {
        return belttle;
    }

    /**
     * @param belttle
     */
    public void setBelttle(Integer belttle) {
        this.belttle = belttle;
    }
}