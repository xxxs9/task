package com.gameloft9.demo.controllers.api;

import com.gameloft9.demo.dataaccess.model.user.WxUser;
import com.gameloft9.demo.dto.user.WxUserDto;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.wxapi.WxUserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 微信用户登录
 */
@Slf4j
@Controller
@RequestMapping("/wxUser")
public class WxUserLoginController {

    @Autowired
    private WxUserLoginService wxUserLoginService;

    /**
     * 小程序授权登录
     * 由于获取不到unionId
     * 暂时用nickname做唯一标识
     * unicode字符未处理
     * @param nickname
     * @param gender
     * @param avatarUrl
     * @return
     */
    @RequestMapping(value = "/login.api",method = RequestMethod.POST)
    @ResponseBody
    public IResult login(String nickname , String gender , String avatarUrl){
        //返回json至前端的均返回ResultBean或者PageResultBean
        WxUser wxUser = new WxUser();
        wxUser.setAvatarUrl(avatarUrl);
        wxUser.setGender(gender);
        wxUser.setNickname(nickname);
        return new ResultBean<WxUserDto>(wxUserLoginService.login(wxUser));
    }
}
