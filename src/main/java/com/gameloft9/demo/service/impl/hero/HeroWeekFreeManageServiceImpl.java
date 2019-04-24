package com.gameloft9.demo.service.impl.hero;

import com.gameloft9.demo.dataaccess.dao.user.HeroWeekFreeMapper;
import com.gameloft9.demo.dataaccess.model.user.HeroWeekFree;
import com.gameloft9.demo.service.api.hero.HeroWeekFreeManageService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroWeekFreeManageServiceImpl implements HeroWeekFreeManageService {

    @Autowired
    private HeroWeekFreeMapper dao;

    @Override
    public List<HeroWeekFree> getAll(String page, String limit, String heroName) {
        PageRange pageRange = new PageRange(page,limit);
        return dao.getAll(pageRange.getStart(),pageRange.getEnd(),heroName);
    }

    @Override
    public Integer countGetAll(String heroName) {
        return dao.countGetAll(heroName);
    }
}
