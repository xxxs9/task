package com.gameloft9.demo.webmagic;

import com.gameloft9.demo.dataaccess.model.user.HeroDetail;
import com.gameloft9.demo.dataaccess.model.user.HeroSkill;
import lombok.Data;

import java.util.List;

@Data
public class HeroReptile {

    private HeroDetail heroDetail;

    private List<HeroSkill> hsList;
}
