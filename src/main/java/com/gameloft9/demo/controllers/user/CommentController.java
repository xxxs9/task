package com.gameloft9.demo.controllers.user;

import com.gameloft9.demo.dataaccess.model.user.UserComment;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.user.CommentService;
import com.gameloft9.demo.service.beans.system.CommentSelectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * 评论功能
 */
@Slf4j
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService service;

    /**
     * 获取所有用户列表
     * @param page 页序
     * @param limit 分页大小
     * */
    @RequestMapping(value = "/commentList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getList(String page, String limit,String commentName){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<CommentSelectResponse>>(service.getAll(page,limit,commentName),service.countGetAll(commentName));
    }

    /**
     * 删除
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除评论")
    public IResult delete(String id){
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
        return new ResultBean<UserComment>(service.getById(id));
    }

    /**
     * 更新
     * */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新评论")
    public IResult update(@RequestBody UserComment comment){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(service.updateComment(comment));
    }

}
