package com.gameloft9.demo.service.impl.hero;

import com.gameloft9.demo.dataaccess.dao.user.HeroBaseMapper;
import com.gameloft9.demo.dataaccess.model.user.HeroBase;
import com.gameloft9.demo.service.api.hero.HeroBaseManageService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 英雄基本数据后台逻辑
 */
@Service
public class HeroBaseManageServiceImpl implements HeroBaseManageService {

    @Autowired
    private HeroBaseMapper dao;

    @Override
    public List<HeroBase> getAll(String page, String limit, String heroName) {
        PageRange pageRange = new PageRange(page,limit);
        return dao.getAll(pageRange.getStart(), pageRange.getEnd(),heroName);
    }

    @Override
    public Integer countGetAll(String heroName) {
        return dao.countGetAll(heroName);
    }
}
