package com.gameloft9.demo.controllers;

import com.gameloft9.demo.dataaccess.dao.user.HeroBaseMapper;
import com.gameloft9.demo.dataaccess.dao.user.HeroDetailMapper;
import com.gameloft9.demo.dataaccess.model.user.HeroDetail;
import com.gameloft9.demo.webmagic.ReptileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    private HeroDetailMapper heroDetailMapper;

    /**
     * 获取英雄基本信息
     */
    @RequestMapping(value = "/test.api",method = RequestMethod.GET)
    @ResponseBody
    public void test(){
//        reptileUtil.getHeroWeekFree();
    }

    /**
     * 获取英雄周免信息
     */
    @RequestMapping(value = "/weekfree.api",method = RequestMethod.GET)
    @ResponseBody
    public void getHeroWeekFree(){
        reptileUtil.getHeroWeekFree();
    }
    /**
     * 获取英雄出装信息
     */
    @RequestMapping(value = "/equip.api",method = RequestMethod.GET)
    @ResponseBody
    public void getHeroEquip() {
        //为防止接口被随意访问导致的重复抓取，将方法注释掉
//        List<HeroDetail> heroDetailList =  heroDetailMapper.selectAll();
//        for (int i = 0, num = heroDetailList.size(); i < num ; i ++ ){
//            if (!StringUtils.isEmpty(heroDetailList.get(i).getEquipId())
//            && heroDetailList.get(i).getEquipId().length() > 0){
//                reptileUtil.getHeroEquipData(heroDetailList.get(i).getEquipId());
//            }
//        }
    }

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
        //为防止接口被随意访问导致的重复抓取，将方法注释掉
//        List<HeroBase> bhList =  heroBaseMapper.getNewList();
//        if (bhList != null && bhList.size() > 0){
//            for (int i = 0, num = bhList.size(); i < num ; i ++ ){
//                reptileUtil.getLolHeroDetail(bhList.get(i).getDetailUrl());
//            }
//        }
    }
}
