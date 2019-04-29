package com.gameloft9.demo.controllers.api;

import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.wxapi.ReptileUserGameService;
import com.gameloft9.demo.service.api.wxapi.UserQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户游戏数据爬取接口
 */
@Slf4j
@Controller
@RequestMapping("/userGD")
public class ReptileUserGameController {

    @Autowired
    private UserQueryService userQueryService;
    @Autowired
    private ReptileUserGameService reptileUserGameService;

    /**
     * 爬取用户基本数据
     * 个人战力
     * 赛季统计
     * @param serverId
     * @param reptileId
     * @return
     */
    @RequestMapping(value = "/getub.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult getGameData(String uuid, String serverId, String reptileId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        if (!StringUtils.isEmpty(serverId) && !StringUtils.isEmpty(reptileId)){
            try {
                //校验用户是否存在
                if (!userQueryService.queryWxUser(uuid)){
                    return new ResultBean<String>("9999","用户不存在");
                }
                //获取用户近期比赛
                reptileUserGameService.getGameData(uuid, serverId, reptileId);
            }catch (Exception e){
                return new ResultBean<String>("9999","系统错误");
            }
            return new ResultBean<String>("0000","请求成功");
        }
        return new ResultBean<String>("9999","参数异常");

    }

    /**
     * 爬取用户近期比赛数据
     * @param serverId
     * @param reptileId
     * @return
     */
    @RequestMapping(value = "/getrm.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult getRecentMatch(String uuid, String serverId, String reptileId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        if (!StringUtils.isEmpty(serverId) && !StringUtils.isEmpty(reptileId)){
            try {
                //校验用户是否存在
                if (!userQueryService.queryWxUser(uuid)){
                    return new ResultBean<String>("9999","用户不存在");
                }
                //获取用户近期比赛
                reptileUserGameService.getRecentMatch(uuid,serverId,reptileId);
            }catch (Exception e){
                return new ResultBean<String>("9999","系统错误");
            }
            return new ResultBean<String>("0000","请求成功");
        }
        return new ResultBean<String>("9999","参数异常");

    }
}
