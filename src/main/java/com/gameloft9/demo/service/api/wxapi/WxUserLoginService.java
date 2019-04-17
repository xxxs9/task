package com.gameloft9.demo.service.api.wxapi;

import com.gameloft9.demo.dataaccess.model.user.WxUser;
import com.gameloft9.demo.dto.user.WxUserDto;

public interface WxUserLoginService {

    WxUserDto login(WxUser wxUser);


}
