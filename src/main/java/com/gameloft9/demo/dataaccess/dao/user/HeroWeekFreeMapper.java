package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.HeroWeekFree;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HeroWeekFreeMapper extends Mapper<HeroWeekFree> {

    /**
     * 清空表
     */
    void truncateTable();

    /**
     * 批量插入表数据
     * @param list
     */
    void insertList(List<HeroWeekFree> list);


    //------- admin -------

    /**
     * 获取英雄周免数据集合
     * @param start
     * @param end
     * @param heroName
     * @return
     */
    List<HeroWeekFree> getAll(
            @Param("start") Integer start,
            @Param("end") Integer end,
            @Param("heroName") String heroName);

    /**
     * 获取数据总条目
     * @param heroName
     * @return
     */
    Integer countGetAll( @Param("heroName") String heroName);
}