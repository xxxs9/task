package com.gameloft9.demo.dataaccess.model.user;

import javax.persistence.*;

/**
 * 用户游戏近期比赛实体
 */
@Table(name = "reptile_user_recent_match")
public class ReptileUserRecentMatch {
    @Column(name = "ID")
    @Id
    private Integer id;

    /**
     * 爬取的ID
     */
    @Column(name = "REPTILE_ID")
    private String reptileId;

    /**
     * 服务器ID
     */
    @Column(name = "SERVER_ID")
    private String serverId;

    /**
     * 英雄名称
     */
    @Column(name = "HERO_NAME")
    private String heroName;

    /**
     * 英雄图片
     */
    @Column(name = "HERO_IMG")
    private String heroImg;

    /**
     * 游戏场次路径
     * 该路径异常，爬取不到数据
     */
    @Column(name = "GAME_URL")
    private String gameUrl;

    /**
     * 游戏模式
     */
    @Column(name = "GAME_TYPE")
    private String gameType;

    /**
     * 结果
     * 胜利/失败
     */
    @Column(name = "RESULT")
    private String result;

    /**
     * 游戏结束时间
     */
    @Column(name = "GAME_TIME")
    private String gameTime;

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
     * @return HERO_IMG
     */
    public String getHeroImg() {
        return heroImg;
    }

    /**
     * @param heroImg
     */
    public void setHeroImg(String heroImg) {
        this.heroImg = heroImg;
    }

    /**
     * @return GAME_URL
     */
    public String getGameUrl() {
        return gameUrl;
    }

    /**
     * @param gameUrl
     */
    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    /**
     * @return GAME_TYPE
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * @param gameType
     */
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    /**
     * @return RESULT
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return GAME_TIME
     */
    public String getGameTime() {
        return gameTime;
    }

    /**
     * @param gameTime
     */
    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }
}