package com.gameloft9.demo.service.impl.wxapi;

import com.gameloft9.demo.dataaccess.dao.user.UserFriendsMapper;
import com.gameloft9.demo.dataaccess.dao.user.WxUserMapper;
import com.gameloft9.demo.dataaccess.model.user.UserFriends;
import com.gameloft9.demo.dataaccess.model.user.WxUser;
import com.gameloft9.demo.dto.friend.FriendDto;
import com.gameloft9.demo.service.api.wxapi.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class FriendServiceImpl implements FriendService {


    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private UserFriendsMapper userFriendsMapper;


    /**
     * 获取用户列表
     * @param uuid
     * @return
     */
    @Override
    public List<WxUser> findUserList(String uuid) {
        return wxUserMapper.queryListExceptUuid(uuid);
    }

    /**
     * 获取好友列表
     * @param uuid 对应 用户1
     * @return
     */
    @Override
    public List<FriendDto> friendList(String uuid) {
        return userFriendsMapper.myFirendListByStatus(uuid,1);
    }

    /**
     * 获取自己发送的申请列表
     * @param uuid
     * @return
     */
    @Override
    public List<FriendDto> myApplyList(String uuid) {
        return userFriendsMapper.myFirendListByStatus(uuid,0);
    }

    /**
     * 发送好友申请请求
     * @param uuid  myId
     * @param friendId
     */
    @Override
    public void applyFriend(String uuid, String friendId) {
        UserFriends ouf = userFriendsMapper.queryFriendByFirstSecondId(uuid,friendId);
        //判断 数据库是否已存在该好友关系-并判断是申请-好友-还是删除关系
        if (ouf != null){
            //如果已经是好友关系 - 不做任何处理
            if (ouf.getFriendStatus() == 1){

            }else{
                //非好友关系，则更新数据条目
                //更新为申请状态
                ouf.setFriendStatus(0);
                userFriendsMapper.updateByPrimaryKey(ouf);
            }
        }else if (ouf == null){
            //数据库不存在则新增该条目
            UserFriends uf = new UserFriends();
            uf.setCreateTime(new Date());
            uf.setFriendStatus(0);
            uf.setLoginNameFirst(uuid);
            uf.setLoginNameSecond(friendId);
            userFriendsMapper.insert(uf);
        }
    }

    /**
     * 删除自己发送的请求
     * @param uuid  myId
     * @param id -- 请求的ID
     */
    @Override
    public void delApply(String uuid, Integer id) {

        UserFriends quf = new UserFriends();
        quf.setId(id);
        userFriendsMapper.selectByPrimaryKey(quf);
        //判断是否存在，是否是自己发送的请求，是否是申请状态
        if (quf != null
                && quf.getLoginNameFirst().equals(uuid)
                && quf.getFriendStatus() == 0){
            //重置状态为删除
            quf.setFriendStatus(2);
            userFriendsMapper.updateByPrimaryKey(quf);
        }
    }

    /**
     * 查看别人发送给自己的好友申请列表
     * @param uuid 对应 用户2
     * @return
     */
    @Override
    public List<FriendDto> otherApplyList(String uuid) {
        return userFriendsMapper.applyListByUuid(uuid);
    }


    /**
     * 好友请求操作
     * @param uuid 注意这个对应表中的用户2Id 也是其他接口的friendId
     * @param id
     * @param status 1同意变成好友 2删除申请条目
     */
    @Override
    public void operationApply(String uuid, Integer id, Integer status) {
        //先校验好友请求是否合法
        UserFriends quf = new UserFriends();
        quf.setId(id);
        quf = userFriendsMapper.selectByPrimaryKey(quf);
        //确实是发送给自己的好友申请 - 并且是请求状态 0
        if (quf != null
                && quf.getLoginNameSecond().equals(uuid)
                && quf.getFriendStatus() == 0){
            // status
            if (status == 2){
                //删除好友申请
                quf.setFriendStatus(2);
                //更新数据
                userFriendsMapper.updateByPrimaryKey(quf);
            }else if (status == 1){
                //同意好友申请
                quf.setFriendStatus(1);

                //双向添加好友关系
                //首先校验对方的好友条目是否存在
                UserFriends ouf = userFriendsMapper.queryFriendByFirstSecondId(quf.getLoginNameSecond(),quf.getLoginNameFirst());
                if (ouf != null ){
                    //对向关系存在 - 更新关系
                    ouf.setFriendStatus(1);
                    userFriendsMapper.updateByPrimaryKey(ouf);
                }else{
                    //对向关系不存在 - 新增关系
                    ouf = new UserFriends();
                    ouf.setFriendStatus(1);
                    ouf.setLoginNameSecond(quf.getLoginNameFirst());
                    ouf.setLoginNameFirst(quf.getLoginNameSecond());
                    ouf.setCreateTime(new Date());
                    userFriendsMapper.insert(ouf);
                }
                //同向关系 - 更新
                userFriendsMapper.updateByPrimaryKey(quf);
            }
        }
    }

    /**
     * 删除好友关系
     * 双向删除
     * @param uuid
     * @param id
     * @param friendId
     */
    @Override
    public void delFriend(String uuid, Integer id, String friendId) {
        //获取同向好友关系
        UserFriends quf = new UserFriends();
        quf.setId(id);
        quf = userFriendsMapper.selectByPrimaryKey(quf);
        if (quf != null){
            //获取对向好友关系
            UserFriends ouf = userFriendsMapper.queryFriendByFirstSecondId(quf.getLoginNameSecond(),quf.getLoginNameFirst());
            if (ouf != null){
                ouf.setFriendStatus(2);
                quf.setFriendStatus(2);
                userFriendsMapper.updateByPrimaryKey(ouf);
                userFriendsMapper.updateByPrimaryKey(quf);
            }else{
                //删除单项关系
                userFriendsMapper.updateByPrimaryKey(quf);
                System.out.println("系统异常:好友删除->没有保持双向关系");
            }
        }else {
            System.out.println("系统异常：好友删除->删除好友的条目异常");
        }

    }
}
