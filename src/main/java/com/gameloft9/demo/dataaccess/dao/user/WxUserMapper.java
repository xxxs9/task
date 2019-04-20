package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.WxUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface WxUserMapper extends Mapper<WxUser> {


    /**
     * 返回除了该uuid的用户列表
     * @param uuid
     * @return
     */
    List<WxUser> queryListExceptUuid(String uuid);
    /**
     * 通过微信昵称获取用户
     * @param nickname
     * @return
     */
    WxUser queryWxUserByNickname(String nickname);
    /**
     * 通过UUID获取用户
     * @param uuid
     * @return
     */
    WxUser queryWxUserByUuid(String uuid);
    /**
     * 通过UNIONID获取用户
     * @param unionid
     * @return
     */
    WxUser queryWxUserByUnionid(String unionid);
}