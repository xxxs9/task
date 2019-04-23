package com.gameloft9.demo.controllers.api;

import com.gameloft9.demo.dataaccess.model.user.ReptileUserRecentMatch;
import com.gameloft9.demo.dto.user.WxUserBaseGameDto;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.wxapi.UserGameDataService;
import com.gameloft9.demo.service.api.wxapi.UserQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户游戏数据获取接口
 */
@Slf4j
@Controller
@RequestMapping("/getUserData")
public class UserGameDataController {


    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private UserGameDataService userGameDataService;


    /**
     * 获取游戏角色的基本信息
     * 包括等级战斗力
     * 各赛季统计数据
     * @return
     */
    @RequestMapping(value = "/base.api")
    @ResponseBody
    public IResult getUserGameData(String uuid){

        if (userQueryService.queryWxUser(uuid)){
            return new ResultBean<WxUserBaseGameDto>(userGameDataService.getUserGameData(uuid));
        }
        return new ResultBean<String>("用户信息错误");
    }

    /**
     * 获取游戏角色的近期比赛数据
     * @return
     */
    @RequestMapping(value = "/match.api")
    @ResponseBody
    public IResult getUserRecentMatch(String uuid){
        if (userQueryService.queryWxUser(uuid)){
            return new ResultBean<List<ReptileUserRecentMatch>>(userGameDataService.getUserRecentMatch(uuid));
        }
        return new ResultBean<String>("用户信息错误");
    }

}
