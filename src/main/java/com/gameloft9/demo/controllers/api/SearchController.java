package com.gameloft9.demo.controllers.api;

import com.gameloft9.demo.dto.SearchDto;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.wxapi.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 搜索模块，直接搜索用户数据
 */
@Slf4j
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * 查询角色数据
     * 有 - 则数据库返回
     * 无 - 尝试爬虫抓取
     */
    @RequestMapping(value = "/quick.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult searchRole(String roleName, String serverName){
        return new ResultBean<SearchDto>(searchService.searchRole(roleName,serverName));
    }
}
