package com.gameloft9.demo.service.impl.wxapi;

import com.gameloft9.demo.dataaccess.dao.user.HeroBaseMapper;
import com.gameloft9.demo.dataaccess.dao.user.HeroDetailMapper;
import com.gameloft9.demo.dataaccess.dao.user.HeroSkillMapper;
import com.gameloft9.demo.dataaccess.model.user.HeroBase;
import com.gameloft9.demo.dataaccess.model.user.HeroDetail;
import com.gameloft9.demo.dataaccess.model.user.HeroSkill;
import com.gameloft9.demo.dto.hero.HeroDetailDto;
import com.gameloft9.demo.service.api.wxapi.ViewGameDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<HeroBase> getAllHero(Integer pageNum, Integer pageSize) {
        return heroBaseMaper.getListByPageNumSize(pageNum,pageSize);
    }

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
