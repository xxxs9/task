package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.HeroBase;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface HeroBaseMapper extends Mapper<HeroBase> {

    /**
     * 获取英雄列表
     * 分页加载
     * @param map
     * @return
     */
    List<HeroBase> getListByPageNumSize(Map map
//            @Param("pageNum") Integer pageNum,
//            @Param("pageSize") Integer pageSize
    );

    /**
     * 获取未爬取的英雄URl
     * @return
     */
    List<HeroBase> getNewList();


    // -------- admin --------
    /**
     * 获取英雄基本数据集合
     * @param start
     * @param end
     * @param heroName
     * @return
     */
    List<HeroBase> getAll(
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