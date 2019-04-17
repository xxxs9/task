package com.gameloft9.demo.controllers.api;


import com.gameloft9.demo.dataaccess.model.user.WxUser;
import com.gameloft9.demo.dataaccess.model.user.WxUserReptileInfo;
import com.gameloft9.demo.dto.user.WxUserDto;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.wxapi.BindGameRoleService;
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
    /**
     * 绑定游戏角色
     * @return
     */
    @RequestMapping(value = "/base.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult bindGameRole(String uuid ,String reptileName, String serverName){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(bindGameRoleService.bindGameRole(uuid ,reptileName,serverName));
    }

    @RequestMapping(value = "/test.api",method = RequestMethod.GET)
    @ResponseBody
    public IResult test(){
        //返回json至前端的均返回ResultBean或者PageResultBean
        bindGameRole("bf037e384e03458bbf94a4fae5283071","悲伤的雪糕","电信一");
        return new ResultBean<String>("接口测试成功");
    }

}