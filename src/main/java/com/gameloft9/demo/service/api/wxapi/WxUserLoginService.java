package com.gameloft9.demo.service.api.wxapi;

import com.gameloft9.demo.dataaccess.model.user.WxUser;
import com.gameloft9.demo.dto.user.WxUserDto;

public interface WxUserLoginService {

    /**
     * 微信用户授权登录
     * @param wxUser
     * @return
     */
    WxUserDto login(WxUser wxUser);


}
