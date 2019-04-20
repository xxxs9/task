package com.gameloft9.demo.service.api.wxapi;

import com.gameloft9.demo.dataaccess.model.user.HeroBase;
import com.gameloft9.demo.dto.hero.HeroDetailDto;

import java.util.List;

public interface ViewGameDataService {

    /**
     * 获取所有英雄
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<HeroBase> getAllHero(Integer pageNum, Integer pageSize);

    /**
     * 获取英雄明细
     * 通过 heroName和HeroTile
     * 因为会出现英雄名或者称号不匹配的现象
     * @param heroName
     * @param heroTile
     * @return
     */
    public HeroDetailDto getHeroDetailSKill(String heroName, String heroTile);
}
