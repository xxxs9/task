package com.gameloft9.demo.controllers.api;

import com.gameloft9.demo.dataaccess.model.user.HeroBase;
import com.gameloft9.demo.dto.hero.HeroDetailDto;
import com.gameloft9.demo.dto.index.BannerDto;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.wxapi.ViewGameDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 小程序英雄信息控制器
 */
@Slf4j
@Controller
@RequestMapping("/wxHero")
public class ViewGameDataController {


    @Autowired
    private ViewGameDataService viewGameDataService;

    /**
     * 获取所有英雄基本信息
     * @return
     */
    @RequestMapping(value = "/all.api",method = RequestMethod.GET)
    @ResponseBody
    public IResult getAllHero(Integer pageNum, Integer pageSize){
        if (pageNum == null || pageNum < 1){
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 2){
            pageSize = 6;
        }
        return new ResultBean<List<HeroBase>>(viewGameDataService.getAllHero(pageNum,pageSize));
    }

    /**
     * 获取指定英雄数据
     * @return
     */
    @RequestMapping(value = "/detail.api",method = RequestMethod.GET)
    @ResponseBody
    public IResult getHeroDetail(String heroName, String heroTitle){
        return new ResultBean<HeroDetailDto>(viewGameDataService.getHeroDetailSKill(heroName,heroTitle));

    }

}
