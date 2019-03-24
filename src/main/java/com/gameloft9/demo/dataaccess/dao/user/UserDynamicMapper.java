package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.UserDynamic;
import com.gameloft9.demo.dataaccess.model.user.UserInformation;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDynamicMapper extends Mapper<UserDynamic>{
    //查找所有条目
    List<UserDynamic> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("loginName") String loginName);
    //条目记录数
    Integer countGetAll(@Param("loginName") String loginName);
}