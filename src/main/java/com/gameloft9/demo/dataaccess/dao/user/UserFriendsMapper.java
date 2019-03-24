package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.UserFriends;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserFriendsMapper extends Mapper<UserFriends>{

    //获取所有资讯内容
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