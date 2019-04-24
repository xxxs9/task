package com.gameloft9.demo.service.impl.hero;

import com.gameloft9.demo.dataaccess.dao.user.HeroDetailMapper;
import com.gameloft9.demo.dataaccess.model.user.HeroDetail;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.hero.HeroDetailManageService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台数据业务逻辑
 */
@Service
public class HeroDetailManageServiceImpl implements HeroDetailManageService {

    @Autowired
    private HeroDetailMapper dao;

    @Override
    public List<HeroDetail> getAll(String page, String limit, String heroName) {
        PageRange pageRange = new PageRange(page,limit);
        return dao.getAll(pageRange.getStart(), pageRange.getEnd(),heroName);
    }

    @Override
    public Integer countGetAll(String heroName) {
        return dao.countGetAll(heroName);
    }

    @Override
    public HeroDetail getById(String id) {
        CheckUtil.notBlank(id,"id为空");
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public Boolean updateHeroDetail(HeroDetail heroDetail) {
        dao.updataHeroDetail(heroDetail);
        return true;
    }
}
