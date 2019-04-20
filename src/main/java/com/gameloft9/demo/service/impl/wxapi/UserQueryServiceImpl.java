package com.gameloft9.demo.service.impl.wxapi;

import com.gameloft9.demo.dataaccess.dao.user.WxUserMapper;
import com.gameloft9.demo.dataaccess.model.user.WxUser;
import com.gameloft9.demo.service.api.wxapi.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 微信小程序uuid校验
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private WxUserMapper wxUserMapper;

    @Override
    public boolean queryWxUser(String uuid) {
        WxUser wxUser = wxUserMapper.queryWxUserByUuid(uuid);
        if (wxUser != null){
            return true;
        }
        return false;
    }
}


