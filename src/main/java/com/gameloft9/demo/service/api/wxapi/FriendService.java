package com.gameloft9.demo.service.api.wxapi;

import com.gameloft9.demo.dataaccess.model.user.UserFriends;
import com.gameloft9.demo.dataaccess.model.user.WxUser;
import com.gameloft9.demo.dto.friend.FriendDto;

import java.util.List;

public interface FriendService {

    /**
     * 发现用户列表
     * 随机发现Or全部返回
     * @param uuid
     * @return
     */
    List<WxUser> findUserList(String uuid);

    /**
     * 获取好友列表
     * @param uuid 对应 用户1
     * @return
     */
    List<FriendDto> friendList(String uuid);

    /**
     * 获取自己发送的申请列表
     * @param uuid
     * @return
     */
    List<FriendDto> myApplyList(String uuid);

    /**
     * 发送好友申请
     * 单项添加申请关系
     * @param uuid  myId
     * @param friendId
     */
    void applyFriend(String uuid, String friendId);

    /**
     * 删除自己发送的请求
     * 单项添加申请关系
     * @param uuid  myId
     * @param id -- 请求的ID
     */
    void delApply(String uuid, Integer id);


    /**
     * 获取别人发送给自己的申请列表
     * @param uuid 对应 用户1
     * @return
     */
    List<FriendDto> otherApplyList(String uuid);

    /**
     * 处理好友申请
     * 成功就双向添加
     * @param uuid 注意这个对应表中的用户2Id 也是其他接口的friendId
     * @param id
     * @param status
     */
    void operationApply(String uuid , Integer id , Integer status);

    /**
     * 删除好友关系
     * 双向删除
     * @param uuid
     * @param id
     * @param friendId
     */
    void delFriend(String uuid, Integer id ,String friendId);


}
