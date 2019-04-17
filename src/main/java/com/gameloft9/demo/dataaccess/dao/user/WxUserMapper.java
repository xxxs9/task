package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.WxUser;
import tk.mybatis.mapper.common.Mapper;

public interface WxUserMapper extends Mapper<WxUser> {

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