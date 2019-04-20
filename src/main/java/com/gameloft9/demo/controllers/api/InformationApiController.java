package com.gameloft9.demo.controllers.api;

import com.gameloft9.demo.dto.index.BannerDto;
import com.gameloft9.demo.dto.index.NewsDto;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.wxapi.InformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 小程序资讯
 */
@Slf4j
@Controller
@RequestMapping("/information")
public class InformationApiController {


    @Autowired
    private InformationService informationService;

    /**
     * 获取滚动栏
     * @return
     */
    @RequestMapping(value = "/banner.api",method = RequestMethod.GET)
    @ResponseBody
    public IResult getBannerList(){
        return new ResultBean<List<BannerDto>>(informationService.getBannerList());

    }

    /**
     * 获取新闻列表
     * @return
     */
    @RequestMapping(value = "/news.api",method = RequestMethod.GET)
    @ResponseBody
    public IResult getNewsList(Integer pageNum, Integer pageSize){
        return new ResultBean<List<NewsDto>>(informationService.getNewsList(pageNum,pageSize));
    }

}
