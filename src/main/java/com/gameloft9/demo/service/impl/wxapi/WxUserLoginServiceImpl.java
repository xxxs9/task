package com.gameloft9.demo.service.impl.wxapi;

import com.gameloft9.demo.dataaccess.dao.user.WxUserMapper;
import com.gameloft9.demo.dataaccess.dao.user.WxUserReptileInfoMapper;
import com.gameloft9.demo.dataaccess.model.user.WxUser;
import com.gameloft9.demo.dataaccess.model.user.WxUserReptileInfo;
import com.gameloft9.demo.dto.user.WxUserDto;
import com.gameloft9.demo.service.api.wxapi.WxUserLoginService;
import com.gameloft9.demo.utils.UUIDUtil;
import com.gameloft9.demo.webmagic.template.ReptileDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 微信登录的业务逻辑
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WxUserLoginServiceImpl implements WxUserLoginService {


    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private WxUserReptileInfoMapper wxUserReptileInfoMapper;

    /**
     * 登录先保存微信用户信息
     * 然后返回用户绑定信息
     * @param wxUser
     * @return
     */
    @Override
    public WxUserDto login(WxUser wxUser) {
        String nickname = ReptileDataUtil.stringToUnicode(wxUser.getNickname());
        wxUser.setNickname(nickname);
        WxUser user = wxUserMapper.queryWxUserByNickname(wxUser.getNickname());
        WxUserDto dto = new WxUserDto();
        if (user != null){
            user.setNickname(wxUser.getNickname());
            user.setAvatarUrl(wxUser.getAvatarUrl());
            wxUserMapper.updateByPrimaryKey(user);
            dto = toWxUserDto(user);
        }else{
            wxUser.setUuid(UUIDUtil.getUUID());
            wxUser.setUnionId("unionId");
            wxUser.setCreateTime(new Date());
            wxUserMapper.insert(wxUser);
            user = wxUserMapper.queryWxUserByNickname(wxUser.getNickname());
            if (user != null) {
                dto = toWxUserDto(user);
            }
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute("wxUser",dto);
        return dto;
    }

    /**
     * 登录返回数据处理
     * @param wxUser
     * @return
     */
    public WxUserDto toWxUserDto(WxUser wxUser){
        WxUserDto wxUserDto = new WxUserDto();

        //获取爬取信息
        WxUserReptileInfo wri = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuid(wxUser.getUuid());
        wxUserDto.setUuid(wxUser.getUuid());
        if (wri != null){
            wxUserDto.setReptileId(wri.getReptileId());
            wxUserDto.setServerId(wri.getServerId());
        }
        return wxUserDto;
    }
}
