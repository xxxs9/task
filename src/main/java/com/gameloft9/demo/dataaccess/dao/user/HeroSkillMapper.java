package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.HeroSkill;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HeroSkillMapper extends Mapper<HeroSkill> {

    /**
     * 通过英雄名获取技能
     * 英雄名必须是hero_detail的英雄名
     * @param heroName
     * @return
     */
    List<HeroSkill> queryListByHeroName(String heroName);
}