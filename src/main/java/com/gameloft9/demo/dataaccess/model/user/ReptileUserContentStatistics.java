package com.gameloft9.demo.dataaccess.model.user;

import javax.persistence.*;

/**
 * 用户赛季统计数据
 */
@Table(name = "reptile_user_content_statistics")
public class ReptileUserContentStatistics {
    @Column(name = "ID")
    @Id
    private Integer id;

    /**
     * 爬虫ID
     */
    @Column(name = "REPTILE_ID")
    private String reptileId;

    /**
     * 服务器ID
     */
    @Column(name = "SERVER_ID")
    private String serverId;

    /**
     * 赛季类型
     * 例如：匹配
     */
    @Column(name = "CONTENT_TYPE")
    private String contentType;

    /**
     * 胜场数
     */
    @Column(name = "WIN")
    private Integer win;

    /**
     * 败场数
     */
    @Column(name = "LOSE")
    private Integer lose;

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
     * @return CONTENT_TYPE
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return WIN
     */
    public Integer getWin() {
        return win;
    }

    /**
     * @param win
     */
    public void setWin(Integer win) {
        this.win = win;
    }

    /**
     * @return LOSE
     */
    public Integer getLose() {
        return lose;
    }

    /**
     * @param lose
     */
    public void setLose(Integer lose) {
        this.lose = lose;
    }
}