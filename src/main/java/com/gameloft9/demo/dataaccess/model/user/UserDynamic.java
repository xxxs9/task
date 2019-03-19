package com.gameloft9.demo.dataaccess.model.user;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_dynamic")
public class UserDynamic {
    /**
     * 朋友圈动态表
     */
    @Column(name = "ID")
    private Integer id;

    /**
     * 发布者名
     */
    @Column(name = "LOGIN_NAME")
    private String loginName;

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
     * 动态内容
     */
    @Column(name = "DYNAMIC_CONTENT")
    private String dynamicContent;

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
}