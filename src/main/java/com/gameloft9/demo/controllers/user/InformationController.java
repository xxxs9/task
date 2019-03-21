package com.gameloft9.demo.controllers.user;

import com.gameloft9.demo.dataaccess.model.user.UserInformation;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.user.InformtionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * 资讯功能
 */
@Slf4j
@Service
@RequestMapping("/common")
public class InformationController {
    @Autowired
    InformtionService service;

    /**
     * 获取所有用户列表
     * @param page 页序
     * @param limit 分页大小
     * */
    @RequestMapping(value = "/informationList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getUserList(String page, String limit, String loginName, String informationTitle, String isTop){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<UserInformation>>(service.getAll(page,limit,loginName,informationTitle,isTop),service.countGetAll(loginName,informationTitle,isTop));
    }

    /**
     * 删除用户
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除资讯")
    public IResult deleteUser(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(service.deleteById(id));
    }
}
