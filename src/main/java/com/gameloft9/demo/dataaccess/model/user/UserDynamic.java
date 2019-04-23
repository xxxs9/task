package com.gameloft9.demo.dataaccess.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_dynamic")
public class UserDynamic {
    /**
     * 朋友圈动态表
     */
    @Column(name = "ID")
    @Id
    private Integer id;

    /**
     * 发布者名
     */
    @Column(name = "LOGIN_NAME")
    private String loginName;

    /**
     * 动态发送者的用户Id
     */
    @Column(name = "UUID")
    private String uuid;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 动态内容
     */
    @Column(name = "DYNAMIC_CONTENT")
    private String dynamicContent;

    /**
     * 是否删除
     * 0不删除 1删除
     */
    @Column(name = "IS_DEL")
    private Integer isDel;

    /**
     * 获取朋友圈动态表
     *
     * @return ID - 朋友圈动态表
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置朋友圈动态表
     *
     * @param id 朋友圈动态表
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取发布者名
     *
     * @return LOGIN_NAME - 发布者名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置发布者名
     *
     * @param loginName 发布者名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_TIME - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取动态内容
     *
     * @return DYNAMIC_CONTENT - 动态内容
     */
    public String getDynamicContent() {
        return dynamicContent;
    }

    /**
     * 设置动态内容
     *
     * @param dynamicContent 动态内容
     */
    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}