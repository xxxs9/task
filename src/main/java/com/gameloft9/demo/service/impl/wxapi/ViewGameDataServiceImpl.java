package com.gameloft9.demo.service.impl.wxapi;

import com.gameloft9.demo.dataaccess.dao.user.*;
import com.gameloft9.demo.dataaccess.model.user.*;
import com.gameloft9.demo.dto.hero.HeroDetailDto;
import com.gameloft9.demo.service.api.wxapi.ViewGameDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 页面显示的英雄数据处理工具
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ViewGameDataServiceImpl implements ViewGameDataService {

    @Autowired
    private HeroBaseMapper heroBaseMaper;

    @Autowired
    private HeroDetailMapper heroDetailMapper;
    @Autowired
    private HeroSkillMapper heroSkillMapper;

    @Autowired
    private HeroWeekFreeMapper heroWeekFreeMapper;

    @Autowired
    private HeroEquipMapper heroEquipMapper;

    @Override
    public HeroEquip getHeroEquip(String equipId) {
        return heroEquipMapper.queryByEquipId(equipId);
    }

    @Override
    public List<HeroWeekFree> getFreeHero() {
        return heroWeekFreeMapper.selectAll();
    }

    @Override
    public List<HeroBase> getAllHero(Map map) {
        return heroBaseMaper.getListByPageNumSize(map);
    }

    /**
     * 获取英雄详情和技能详情
     * @param heroName
     * @param heroTitle
     * @return
     */
    @Override
    public HeroDetailDto getHeroDetailSKill(String heroName, String heroTitle) {
        HeroDetailDto dto = new HeroDetailDto();
        HeroDetail hd = heroDetailMapper.queryHeroDetailByHeroNameOrHeroTile(heroName, heroTitle);
        if (hd != null){
            dto.setHeroDetail(hd);
            List<HeroSkill> hsList = heroSkillMapper.queryListByHeroName(hd.getHeroName());
            if (hsList != null && hsList.size() > 0) {
                dto.setSkillList(hsList);
            }
        }
        return dto;
    }

}
