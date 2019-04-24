package com.gameloft9.demo.service.api.hero;

import com.gameloft9.demo.dataaccess.model.user.HeroWeekFree;

import java.util.List;

public interface HeroWeekFreeManageService {

    /**
     * 英雄周免信息列表
     * @param page
     * @param limit
     * @param heroName
     * @return
     */
    List<HeroWeekFree> getAll(String page, String limit, String heroName);

    /**
     * 获取数据总条目数
     * @param heroName
     * @return
     */
    Integer countGetAll(String heroName);
}
