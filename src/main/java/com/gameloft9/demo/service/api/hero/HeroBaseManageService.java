package com.gameloft9.demo.service.api.hero;

import com.gameloft9.demo.dataaccess.model.user.HeroBase;

import java.util.List;

public interface HeroBaseManageService {

    /**
     * 英雄基本信息列表
     * @param page
     * @param limit
     * @param heroName
     * @return
     */
    List<HeroBase> getAll(String page, String limit, String heroName);

    /**
     * 获取数据总条目数
     * @param heroName
     * @return
     */
    Integer countGetAll(String heroName);
}
