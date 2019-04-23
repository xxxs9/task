package com.gameloft9.demo.controllers.api;

import com.gameloft9.demo.dto.dynamic.DynamicDto;
import com.gameloft9.demo.dto.dynamic.UserCommentDto;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.wxapi.UserQueryService;
import com.gameloft9.demo.service.api.wxapi.WxUserDynamicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户动态-评论控制层
 */
@Slf4j
@Controller
@RequestMapping("/wxDynamic")
public class WxUserDynamicController {

    @Autowired
    private WxUserDynamicService wxUserDynamicService;

    @Autowired
    private UserQueryService userQueryService;

    @RequestMapping(value = "/test.api",method = RequestMethod.GET)
    @ResponseBody
    public IResult test(){
        //返回json至前端的均返回ResultBean或者PageResultBean
//        System.out.println("发送动态："+pushDynamic("cs001","动态1"));
//        System.out.println("提交评论："+pushComment(2,"cs001","shdia"));
//        System.out.println("删除评论："+delComment("cs001",1));

        return new ResultBean<String>("接口测试成功");
    }


    /**
     * 获取好友动态
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/dynList.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult findFriendDynamic(String uuid) {
        try {
            if (userQueryService.queryWxUser(uuid)){
                return new ResultBean<List<DynamicDto>>(wxUserDynamicService.findFriendDynamic(uuid));
            }
            return new ResultBean<String>("9999","无效用户");
        }catch (Exception e){
            return new ResultBean<String>("9999","获取失败");
        }
    }

    /**
     * 发送动态
     * @param uuid
     * @param content
     * @return
     */
    @RequestMapping(value = "/pushDyn.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult pushDynamic(String uuid, String content) {
        try {
            if (userQueryService.queryWxUser(uuid)){
                return new ResultBean<Boolean>(wxUserDynamicService.pushDynamic(uuid,content));
            }
            return new ResultBean<String>("9999","无效用户");
        }catch (Exception e){
            return new ResultBean<String>("9999","发送动态失败");
        }
    }

    /**
     * 提交评论
     * @param dynamicId
     * @param uuid
     * @param content
     * @return
     */
    @RequestMapping(value = "/pushCom.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult pushComment(Integer dynamicId, String uuid, String content) {
        try {
            if (userQueryService.queryWxUser(uuid)){
                return new ResultBean<List<UserCommentDto>>(wxUserDynamicService.pushComment(dynamicId,uuid,content));
            }
            return new ResultBean<String>("9999","无效用户");
        }catch (Exception e){
            return new ResultBean<String>("9999","评论失败");
        }
    }

    /**
     * 删除动态
     * @param uuid
     * @param dynamicId
     * @return
     */
    @RequestMapping(value = "/delDyn.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult delDynamic(String uuid, Integer dynamicId) {
        try {
            if (userQueryService.queryWxUser(uuid)){
                return new ResultBean<Boolean>(wxUserDynamicService.delDynamic(uuid, dynamicId));
            }
            return new ResultBean<String>("9999","无效用户");
        }catch (Exception e){
            return new ResultBean<String>("9999","删除动态失败");
        }
    }

    /**
     * 删除评论
     * @param uuid
     * @param commentId
     * @return
     */
    @RequestMapping(value = "/delCom.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult delComment(String uuid, Integer commentId) {
        try {
            if (userQueryService.queryWxUser(uuid)){
                return new ResultBean<Boolean>(wxUserDynamicService.delComment(uuid, commentId));
            }
            return new ResultBean<String>("9999","无效用户");
        }catch (Exception e){
            return new ResultBean<String>("9999","删除评论失败");
        }
    }

}
