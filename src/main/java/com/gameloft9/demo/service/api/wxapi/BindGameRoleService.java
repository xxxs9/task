package com.gameloft9.demo.service.api.wxapi;

import com.gameloft9.demo.dataaccess.model.user.WxUserReptileInfo;

public interface BindGameRoleService {

    /**
     * 绑定游戏角色
     * @return
     */
    Boolean bindGameRole(String uuid ,String reptileName, String serverName);
}
