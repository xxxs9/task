package com.gameloft9.demo.dataaccess.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_friends")
public class UserFriends {
    /**
     * 朋友表
     */
    @Column(name = "ID")
    @Id
    private Integer id;

    /**
     * 用户1
     */
    @Column(name = "LOGIN_NAME_FIRST")
    private String loginNameFirst;

    /**
     * 用户2
     */
    @Column(name = "LOGIN_NAME_SECOND")
    private String loginNameSecond;

    /**
     * 添加时间
     */
    @Column(name = "CREATE_TIME")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 获取朋友表
     *
     * @return ID - 朋友表
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置朋友表
     *
     * @param id 朋友表
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户1
     *
     * @return LOGIN_NAME_FIRST - 用户1
     */
    public String getLoginNameFirst() {
        return loginNameFirst;
    }

    /**
     * 设置用户1
     *
     * @param loginNameFirst 用户1
     */
    public void setLoginNameFirst(String loginNameFirst) {
        this.loginNameFirst = loginNameFirst;
    }

    /**
     * 获取用户2
     *
     * @return LOGIN_NAME_SECOND - 用户2
     */
    public String getLoginNameSecond() {
        return loginNameSecond;
    }

    /**
     * 设置用户2
     *
     * @param loginNameSecond 用户2
     */
    public void setLoginNameSecond(String loginNameSecond) {
        this.loginNameSecond = loginNameSecond;
    }

    /**
     * 获取添加时间
     *
     * @return CREATE_TIME - 添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置添加时间
     *
     * @param createTime 添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}