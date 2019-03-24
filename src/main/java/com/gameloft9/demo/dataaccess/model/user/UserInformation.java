package com.gameloft9.demo.dataaccess.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_information")
public class UserInformation {
    /**
     * 资讯表
     */
    @Column(name = "ID")
    @Id
    private String id;

    /**
     * 发布者名
     */
    @Column(name = "LOGIN_NAME")
    private String loginName;

    /**
     * 资讯标题
     */
    @Column(name = "INFORMATION_TITLE")
    private String informationTitle;

    /**
     * 是否置顶
     */
    @Column(name = "IS_TOP")
    private Integer isTop;

    /**
     * 资讯封面
     */
    @Column(name = "INFORMATION_IMG")
    private String informationImg;

    /**
     * 资讯类型
     */
    @Column(name = "INFORMATION_TYPE")
    private Integer informationType;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 资讯内容
     */
    @Column(name = "INFORMATION_CONTENT")
    private String informationContent;

    /**
     * 获取资讯表
     *
     * @return ID - 资讯表
     */
    public String getId() {
        return id;
    }

    /**
     * 设置资讯表
     *
     * @param id 资讯表
     */
    public void setId(String id) {
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
     * 获取资讯标题
     *
     * @return INFORMATION_TITLE - 资讯标题
     */
    public String getInformationTitle() {
        return informationTitle;
    }

    /**
     * 设置资讯标题
     *
     * @param informationTitle 资讯标题
     */
    public void setInformationTitle(String informationTitle) {
        this.informationTitle = informationTitle;
    }

    /**
     * 获取是否置顶
     *
     * @return IS_TOP - 是否置顶
     */
    public Integer getIsTop() {
        return isTop;
    }

    /**
     * 设置是否置顶
     *
     * @param isTop 是否置顶
     */
    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    /**
     * 获取资讯封面
     *
     * @return INFORMATION_IMG - 资讯封面
     */
    public String getInformationImg() {
        return informationImg;
    }

    /**
     * 设置资讯封面
     *
     * @param informationImg 资讯封面
     */
    public void setInformationImg(String informationImg) {
        this.informationImg = informationImg;
    }

    /**
     * 获取资讯类型
     *
     * @return INFORMATION_TYPE - 资讯类型
     */
    public Integer getInformationType() {
        return informationType;
    }

    /**
     * 设置资讯类型
     *
     * @param informationType 资讯类型
     */
    public void setInformationType(Integer informationType) {
        this.informationType = informationType;
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
     * 获取资讯内容
     *
     * @return INFORMATION_CONTENT - 资讯内容
     */
    public String getInformationContent() {
        return informationContent;
    }

    /**
     * 设置资讯内容
     *
     * @param informationContent 资讯内容
     */
    public void setInformationContent(String informationContent) {
        this.informationContent = informationContent;
    }
}