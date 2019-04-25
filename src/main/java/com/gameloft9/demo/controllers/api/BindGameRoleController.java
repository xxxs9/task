package com.gameloft9.demo.controllers.api;


import com.gameloft9.demo.dto.user.WxUserDto;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.wxapi.BindGameRoleService;
import com.gameloft9.demo.service.api.wxapi.UserQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户绑定游戏角色
 */
@Slf4j
@Controller
@RequestMapping("/bind")
public class BindGameRoleController {


    @Autowired
    private BindGameRoleService bindGameRoleService;
    @Autowired
    private UserQueryService userQueryService;

    /**
     * 绑定游戏角色
     * @return
     */
    @RequestMapping(value = "/base.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult bindGameRole(String uuid ,String reptileName, String serverName){
        if (!userQueryService.queryWxUser(uuid)){
            return new ResultBean<String>("无效用户");
        }
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(bindGameRoleService.bindGameRole(uuid ,reptileName,serverName));
    }



    /**
     * 解除绑定游戏角色
     * @return
     */
    @RequestMapping(value = "/delBase.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult delBindGameRole(String uuid ,String reptileId){
        if (!userQueryService.queryWxUser(uuid)){
            return new ResultBean<String>("无效用户");
        }
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(bindGameRoleService.delBindGameRole(uuid ,reptileId));
    }

    /**
     * 获取游戏角色
     * @return
     */
    @RequestMapping(value = "/getBase.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult getBase(String uuid){
        if (!userQueryService.queryWxUser(uuid)){
            return new ResultBean<String>("无效用户");
        }
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<WxUserDto>(bindGameRoleService.getBase(uuid));
    }

    @RequestMapping(value = "/test.api",method = RequestMethod.GET)
    @ResponseBody
    public IResult test(){
        //返回json至前端的均返回ResultBean或者PageResultBean
        bindGameRole("bf037e384e03458bbf94a4fae5283071","悲伤的雪糕","电信一");
        return new ResultBean<String>("接口测试成功");
    }

}
