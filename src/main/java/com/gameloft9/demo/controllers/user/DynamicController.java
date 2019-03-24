package com.gameloft9.demo.controllers.user;

import com.gameloft9.demo.dataaccess.model.user.UserDynamic;
import com.gameloft9.demo.dataaccess.model.user.UserInformation;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.user.DynamicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Collection;

/**
 * 动态功能
 */
@Slf4j
@Controller
@RequestMapping("/dynamic")
public class DynamicController {

    @Autowired
    DynamicService service;

    /**
     * 获取所有用户列表
     * @param page 页序
     * @param limit 分页大小
     * */
    @RequestMapping(value = "/dynamicList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getList(String page, String limit, String loginName){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<UserDynamic>>(service.getAll(page,limit,loginName),service.countGetAll(loginName));
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

    /**
     * 获取动态
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDynamic(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<UserDynamic>(service.getById(id));
    }
    /**
     * 更新
     * */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新动态")
    public IResult update(@RequestBody UserDynamic dynamic){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(service.updateDynamic(dynamic));
    }

}
