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
     * 绑定游戏角色
     * @return
     */
    Boolean bindGameRole(String uuid ,String reptileName, String serverName);
}
