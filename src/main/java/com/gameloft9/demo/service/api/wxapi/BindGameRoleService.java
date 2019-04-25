package com.gameloft9.demo.service.api.wxapi;

import com.gameloft9.demo.dto.user.WxUserDto;

public interface BindGameRoleService {

    /**
     * 获取用户游戏角色信息
     * @param uuid
     * @return
     */
    WxUserDto getBase(String uuid);

    /**
     * 解除绑定
     * @param uuid
     * @param reptileId
     * @return
     */
    Boolean delBindGameRole(String uuid ,String reptileId);

    /**
     * 绑定游戏角色
     * @param uuid
     * @param reptileName
     * @param serverName
     * @return
     */
    Boolean bindGameRole(String uuid ,String reptileName, String serverName);
}
