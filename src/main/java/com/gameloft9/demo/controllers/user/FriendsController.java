package com.gameloft9.demo.controllers.user;

import com.gameloft9.demo.dataaccess.model.user.UserDynamic;
import com.gameloft9.demo.dataaccess.model.user.UserFriends;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.user.FriendsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * 好友管理
 */
@Slf4j
@Controller
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    FriendsService service;

    /**
     * 获取所有用户列表
     * @param page 页序
     * @param limit 分页大小
     * */
    @RequestMapping(value = "/friendsList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getList(String page, String limit, String loginName){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<UserFriends>>(service.getAll(page,limit,loginName),service.countGetAll(loginName));
    }

    /**
     * 删除
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除资讯")
    public IResult deleteDynamic(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(service.deleteById(id));
    }
}
