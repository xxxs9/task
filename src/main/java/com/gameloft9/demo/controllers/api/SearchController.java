package com.gameloft9.demo.controllers.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 搜索模块，直接搜索用户数据
 */
@Slf4j
@Controller
@RequestMapping("/search")
public class SearchController {


    /**
     * 查询角色数据
     * 有 - 则数据库返回
     * 无 - 尝试爬虫抓取
     */
    public void searchRole(String roleName, String serverName){

    }
}
