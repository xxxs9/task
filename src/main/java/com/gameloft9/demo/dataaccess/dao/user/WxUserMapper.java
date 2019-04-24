package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.WxUser;
import org.apache.ibatis.annotations.Param;
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


    //-------- admin ---------

    /**
     * 获取微信用户数据
     * @param start
     * @param end
     * @param nickname
     * @return
     */
    List<WxUser> getAll(
            @Param("start") Integer start,
            @Param("end") Integer end,
            @Param("nickname") String nickname);

    /**
     * 获取数据总条目
     * @param nickname
     * @return
     */
    Integer countGetAll( @Param("nickname") String nickname);
}