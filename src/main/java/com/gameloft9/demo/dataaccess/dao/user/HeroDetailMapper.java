package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.HeroDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HeroDetailMapper extends Mapper<HeroDetail> {

    /**
     * 通过英雄名或者称号获取英雄
     * @param heroName
     * @param heroTitle
     * @return
     */
    HeroDetail queryHeroDetailByHeroNameOrHeroTile(
            @Param("heroName") String heroName,
            @Param("heroTitle") String heroTitle);

    // -------- admin --------
    /**
     * 获取英雄详细数据集合
     * @param start
     * @param end
     * @param heroName
     * @return
     */
    List<HeroDetail> getAll(
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