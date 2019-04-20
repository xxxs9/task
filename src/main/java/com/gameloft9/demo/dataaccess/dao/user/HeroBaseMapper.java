package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.HeroBase;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HeroBaseMapper extends Mapper<HeroBase> {

    /**
     * 获取英雄列表
     * 分页加载
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<HeroBase> getListByPageNumSize(
            @Param("pageNum") Integer pageNum,
            @Param("pageSize") Integer pageSize);

    /**
     * 获取未爬取的英雄URl
     * @return
     */
    List<HeroBase> getNewList();
}