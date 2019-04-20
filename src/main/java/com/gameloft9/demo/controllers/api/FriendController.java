package com.gameloft9.demo.controllers.api;


import com.gameloft9.demo.dataaccess.model.user.UserFriends;
import com.gameloft9.demo.dataaccess.model.user.WxUser;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.wxapi.FriendService;
import com.gameloft9.demo.service.api.wxapi.UserQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 朋友模块接口
 */
@Slf4j
@Controller
@RequestMapping("/friend")
public class FriendController {


    @Autowired
    private UserQueryService userQueryService;
    @Autowired
    private FriendService friendService;

    /**
     * 发现用户列表
     * @return
     */
    @RequestMapping(value = "/findList.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult findUserList(String uuid){
        try {
            return new ResultBean<List<WxUser>>(friendService.findUserList(uuid));
        }catch (Exception e){
            return new ResultBean<String>("9999","获取用户列表失败");
        }
    }

    /**
     * 获取好友列表
     * @return
     */
    @RequestMapping(value = "/friendList.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult friendList(String uuid){
        try {
            if (userQueryService.queryWxUser(uuid)){
                return new ResultBean<List<UserFriends>>(friendService.friendList(uuid));
            }
            return new ResultBean<String>("9999","无效用户");
        }catch (Exception e){
            return new ResultBean<String>("9999","获取好友列表失败");
        }
    }

    /**
     * 获取自己发送的申请列表
     * @return
     */
    @RequestMapping(value = "/myApplyList.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult myApplyList(String uuid){
        try {
            if (userQueryService.queryWxUser(uuid)){
                return new ResultBean<List<UserFriends>>(friendService.myApplyList(uuid));
            }
            return new ResultBean<String>("9999","无效用户");
        }catch (Exception e){
            return new ResultBean<String>("9999","获取已发送的申请列表失败");
        }
    }

    /**
     * 发送添加好友申请
     * @return
     */
    @RequestMapping(value = "/apply.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult applyFriend(String uuid, String friendId){

        try {
            //无效好友id和uuid==friendId的请求都会被过滤
            if (!userQueryService.queryWxUser(friendId)
                    || uuid.equals(friendId)){
                return new ResultBean<String>("9999","添加的用户为无效用户");
            }
            if (userQueryService.queryWxUser(uuid)){
                friendService.applyFriend(uuid,friendId);
                return new ResultBean<String>("0000","发送成功");
            }
            return new ResultBean<String>("9999","无效用户");
        }catch (Exception e){
            return new ResultBean<String>("9999","发送好友申请失败");
        }
    }

    /**
     * 删除自己发送的好友申请
     * @return
     */
    @RequestMapping(value = "/delApply.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult delApply(String uuid, Integer id){

        try {
            if (userQueryService.queryWxUser(uuid)){
                friendService.delApply(uuid, id);
                return new ResultBean<String>("0000","删除申请成功");
            }
            return new ResultBean<String>("9999","无效用户");
        }catch (Exception e){
            return new ResultBean<String>("9999","删除申请失败");
        }
    }

    /**
     * 获取别人发送给我的的申请列表
     * @return
     */
    @RequestMapping(value = "/otherApplyList.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult otherApplyList(String uuid){
        try {
            if (userQueryService.queryWxUser(uuid)){
                return new ResultBean<List<UserFriends>>(friendService.otherApplyList(uuid));
            }
            return new ResultBean<String>("9999","无效用户");
        }catch (Exception e){
            return new ResultBean<String>("9999","获取申请列表失败");
        }
    }

    /**
     * 处理好友请求
     * @return
     */
    public IResult operationApply(String uuid , Integer id , Integer status){
        try {
            if (userQueryService.queryWxUser(uuid)){
                friendService.operationApply(uuid, id, status);
                return new ResultBean<String>("0000","操作成功");
            }
            return new ResultBean<String>("9999","无效用户");
        }catch (Exception e){
            return new ResultBean<String>("9999","操作失败");
        }
    }


    /**
     * 删除好友
     * @return
     */
    @RequestMapping(value = "/delete.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult delFriend(String uuid, Integer id ,String friendId){
        try {
            if (!userQueryService.queryWxUser(friendId)
                    || uuid.equals(friendId)){
                return new ResultBean<String>("9999","对向用户为无效用户");
            }
            if (userQueryService.queryWxUser(uuid)){
                friendService.delFriend(uuid, id, friendId);
                return new ResultBean<String>("0000","删除成功");
            }
            return new ResultBean<String>("9999","无效用户");
        }catch (Exception e){
            return new ResultBean<String>("9999","删除失败");
        }
    }

}
