package com.gameloft9.demo.controllers;

import com.gameloft9.demo.dataaccess.dao.user.HeroBaseMapper;
import com.gameloft9.demo.dataaccess.model.user.HeroBase;
import com.gameloft9.demo.webmagic.ReptileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.List;

/**
 * 获取英雄数据
 */
@Slf4j
@Controller
@RequestMapping("/gethero")
public class GameHeroController {

    @Autowired
    private ReptileUtil reptileUtil;
    @Autowired
    private HeroBaseMapper heroBaseMapper;

    /**
     * 获取英雄基本信息
     */
    @RequestMapping(value = "/base.api",method = RequestMethod.GET)
    @ResponseBody
    public void getHero(){
        reptileUtil.getLolHeroData();
    }

    /**
     * 获取英雄详细信息
     */
    @RequestMapping(value = "/detail.api",method = RequestMethod.GET)
    @ResponseBody
    public void getHeroDetail() {
        List<HeroBase> bhList =  heroBaseMapper.getNewList();
        if (bhList != null && bhList.size() > 0){
            for (int i = 0, num = bhList.size(); i < num ; i ++ ){
                reptileUtil.getLolHeroDetail(bhList.get(i).getDetailUrl());
            }
        }
    }
}
