package com.gameloft9.demo.controllers.hero;

/**
 * 英雄数据管理
 */

import com.gameloft9.demo.dataaccess.model.user.HeroDetail;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.service.api.hero.HeroDetailManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * 英雄详细数据管理
 */
@Slf4j
@Controller
@RequestMapping("/adminHeroDetail")
public class HeroDetailManageController {


    @Autowired
    private HeroDetailManageService service;

    @RequestMapping(value = "/list.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getList(String page, String limit, String heroName){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<HeroDetail>>(service.getAll(page,limit,heroName),service.countGetAll(heroName));
    }
}
