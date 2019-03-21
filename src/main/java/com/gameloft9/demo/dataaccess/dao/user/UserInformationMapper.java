package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.UserInformation;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserInformationMapper extends Mapper<UserInformation> {

    //获取所有资讯内容
    List<UserInformation> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("loginName") String loginName,
            @Param("informationTitle") String informationTitle,
            @Param("isTop") String isTop);

    /**
     * 获取列表总数
     * @param loginName
     * @param informationTitle
     * @param isTop
     * @return
     */
    int countGetAll(@Param("loginName") String loginName,
                    @Param("informationTitle") String informationTitle,
                    @Param("isTop") String isTop);
    //根据id删除指定记录
    int deleteById(String id);
}