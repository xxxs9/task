package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.HeroDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

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
}