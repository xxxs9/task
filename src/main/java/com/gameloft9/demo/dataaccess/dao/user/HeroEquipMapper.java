package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.HeroEquip;
import tk.mybatis.mapper.common.Mapper;

public interface HeroEquipMapper extends Mapper<HeroEquip> {

    HeroEquip queryByEquipId(String equipId);
}