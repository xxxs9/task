package com.gameloft9.demo.controllers.user;

import com.gameloft9.demo.dataaccess.model.user.WxUser;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.service.api.user.WxUserManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * 微信用户管理
 */
@Slf4j
@Controller
@RequestMapping("/adminWxUser")
public class WxUserManageController {

    @Autowired
    private WxUserManageService service;

    /**
     * 获取所有微信用户列表
     * @param page 页序
     * @param limit 分页大小
     * */
    @RequestMapping(value = "/userList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getList(String page, String limit, String username){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<WxUser>>(service.getAll(page,limit,username),service.countGetAll(username));
    }

}
