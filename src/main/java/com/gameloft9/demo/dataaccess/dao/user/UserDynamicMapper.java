package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.UserDynamic;
import com.gameloft9.demo.dto.dynamic.UserDynamicDto;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDynamicMapper extends Mapper<UserDynamic>{


    /**
     * 获取朋友们的动态
     * @param uuid
     * @return
     */
    List<UserDynamicDto> getFriendDynamic(String uuid);

    /**
     * 查找所有条目
     * @param start
     * @param end
     * @param loginName
     * @return
     */
    List<UserDynamic> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("loginName") String loginName);

    /**
     * 条目记录数
     * @param loginName
     * @return
     */
    Integer countGetAll(@Param("loginName") String loginName);
}