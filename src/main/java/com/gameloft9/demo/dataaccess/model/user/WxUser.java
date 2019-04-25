package com.gameloft9.demo.dataaccess.model.user;

import com.gameloft9.demo.webmagic.template.ReptileDataUtil;

import java.util.Date;
import javax.persistence.*;

/**
 * 微信用户实体
 */
@Table(name = "wx_user")
public class WxUser {

    /**
     * 用户唯一标识
     */
    @Column(name = "UUID")
    @Id
    private String uuid;

    /**
     * 微信昵称
     */
    @Column(name = "NICKNAME")
    private String nickname;

    /**
     * 微信头像
     */
    @Column(name = "AVATAR_URL")
    private String avatarUrl;

    /**
     * 性别
     */
    @Column(name = "GENDER")
    private String gender;

    /**
     * 微信唯一标识
     */
    @Column(name = "UNION_ID")
    private String unionId;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * @return UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return NICKNAME
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return AVATAR_URL
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * @param avatarUrl
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * @return GENDER
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return UNION_ID
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * @param unionId
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return UPDATE_TIME
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}