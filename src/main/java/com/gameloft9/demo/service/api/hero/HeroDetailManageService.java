package com.gameloft9.demo.service.api.hero;

import com.gameloft9.demo.dataaccess.model.user.HeroDetail;

import java.util.List;

/**
 * 英雄详细信息后台业务逻辑
 */
public interface HeroDetailManageService {

    /**
     * 英雄详情信息列表
     * @param page
     * @param limit
     * @param heroName
     * @return
     */
    List<HeroDetail> getAll(String page, String limit, String heroName);

    /**
     * 获取数据总条目数
     * @param heroName
     * @return
     */
    Integer countGetAll(String heroName);
}
