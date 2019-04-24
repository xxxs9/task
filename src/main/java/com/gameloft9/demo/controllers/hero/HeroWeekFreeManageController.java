package com.gameloft9.demo.controllers.hero;


import com.gameloft9.demo.dataaccess.model.user.HeroWeekFree;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.service.api.hero.HeroWeekFreeManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * 英雄周免数据管理
 */
@Slf4j
@Controller
@RequestMapping("/adminHeroWeekFree")
public class HeroWeekFreeManageController {

    @Autowired
    private HeroWeekFreeManageService service;

    @RequestMapping(value = "/list.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getList(String page, String limit, String heroName){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<HeroWeekFree>>(service.getAll(page,limit,heroName),service.countGetAll(heroName));
    }
}
