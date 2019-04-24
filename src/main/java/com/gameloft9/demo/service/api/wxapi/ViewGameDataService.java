package com.gameloft9.demo.service.api.wxapi;

import com.gameloft9.demo.dataaccess.model.user.HeroBase;
import com.gameloft9.demo.dataaccess.model.user.HeroEquip;
import com.gameloft9.demo.dataaccess.model.user.HeroWeekFree;
import com.gameloft9.demo.dto.hero.HeroDetailDto;

import java.util.List;
import java.util.Map;

public interface ViewGameDataService {

    /**
     * 获取英雄出装明细
     * @param equipId
     * @return
     */
    HeroEquip getHeroEquip(String equipId);
    /**
     * 获取周免英雄集合
     * @return
     */
    List<HeroWeekFree> getFreeHero();
    /**
     * 获取所有英雄
     * @param map
     * @return
     */
    List<HeroBase> getAllHero(Map map);

    /**
     * 获取英雄明细
     * 通过 heroName和HeroTile
     * 因为会出现英雄名或者称号不匹配的现象
     * @param heroName
     * @param heroTile
     * @return
     */
    HeroDetailDto getHeroDetailSKill(String heroName, String heroTile);
}
