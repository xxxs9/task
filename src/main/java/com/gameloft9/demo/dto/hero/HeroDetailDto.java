package com.gameloft9.demo.dto.hero;

import com.gameloft9.demo.dataaccess.model.user.HeroDetail;
import com.gameloft9.demo.dataaccess.model.user.HeroSkill;
import lombok.Data;

import java.util.List;

/**
 * 英雄详情返回实体
 */
@Data
public class HeroDetailDto {

    private HeroDetail heroDetail;
    /**
     * 技能
     */
    private List<HeroSkill> skillList;


}
