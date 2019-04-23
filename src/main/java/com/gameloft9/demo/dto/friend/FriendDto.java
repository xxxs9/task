package com.gameloft9.demo.dto.friend;

import lombok.Data;

import java.util.Date;

/**
 * 朋友模块实体
 */
@Data
public class FriendDto {

    /**
     * 朋友表
     */
    private Integer id;

    /**
     * 用户1 发起人 uuid
     */
    private String loginNameFirst;

    /**
     * 用户2 添加人 uuid
     */
    private String loginNameSecond;

    /**
     * 状态 0 申请 1 好友 2 不是好友
     */
    private Integer friendStatus;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 性别
     */
    private String gender;
}
