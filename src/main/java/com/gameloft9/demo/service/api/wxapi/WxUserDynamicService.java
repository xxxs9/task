package com.gameloft9.demo.service.api.wxapi;

import com.gameloft9.demo.dto.dynamic.DynamicDto;

import java.util.List;

/**
 * 好友动态模块逻辑接口
 */
public interface WxUserDynamicService {

    /**
     * 获取好友动态
     * @param uuid
     * @return
     */
    List<DynamicDto> findFriendDynamic(String uuid);

    /**
     * 发送动态
     * @param uuid
     * @param content
     * @return
     */
    boolean pushDynamic(String uuid, String content);

    /**
     * 提交评论
     * @param dynamicId
     * @param uuid
     * @param content
     * @return
     */
    boolean pushComment(Integer dynamicId, String uuid, String content);

    /**
     * 删除动态
     * @param uuid
     * @param dynamicId
     * @return
     */
    boolean delDynamic(String uuid, Integer dynamicId);

    /**
     * 删除评论
     * @param uuid
     * @param commentId
     * @return
     */
    boolean delComment(String uuid, Integer commentId);

}
