package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.UserFriends;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserFriendsMapper extends Mapper<UserFriends>{

    /**
     * 获取自己的好友/已发送的申请列表
     * @param uuid   -- loginNameFirst
     * @param status 0 申请 1 好友
     * @return
     */
    List<UserFriends> myFirendListByStatus(
            @Param("loginNameFirst") String uuid,
            @Param("status") int status);

    /**
     * 获取自己受到的申请列表
     * @param uuid
     * @return
     */
    List<UserFriends> applyListByUuid(String uuid);

    /**
     * 通过2个id获取唯一数据
     * @param uuid
     * @param friendId
     * @return
     */
    UserFriends queryFriendByFirstSecondId(
            @Param("loginNameFirst") String uuid,
            @Param("loginNameSecond") String friendId);


    ///-------- admin ---------

    /**
     * 获取所有好友内容
     * @param start
     * @param end
     * @param loginName
     * @return
     */
    List<UserFriends> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("loginName") String loginName);

    /**
     * 获取列表总数
     * @param loginName
     * @return
     */
    int countGetAll(@Param("loginName") String loginName);
}