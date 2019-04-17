package com.gameloft9.demo.service.impl.wxapi;

import com.gameloft9.demo.dataaccess.dao.user.GetReptileIdMapper;
import com.gameloft9.demo.dataaccess.dao.user.WxUserReptileInfoMapper;
import com.gameloft9.demo.dataaccess.model.user.GetReptileId;
import com.gameloft9.demo.dataaccess.model.user.WxUserReptileInfo;
import com.gameloft9.demo.service.api.wxapi.BindGameRoleService;
import com.gameloft9.demo.webmagic.ReptileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


/**
 * 用户绑定角色的业务逻辑
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BindGameRoleServiceImpl implements BindGameRoleService {


    @Autowired
    private WxUserReptileInfoMapper wxUserReptileInfoMapper;

    @Autowired
    private GetReptileIdMapper getReptileIdMapper;

    @Autowired
    private ReptileUtil reptileUtil;
    /**
     * 绑定角色信息
     * @param reptileName
     * @param serverName
     * @return
     */
    @Override
    public Boolean bindGameRole(String uuid ,String reptileName, String serverName) {

        WxUserReptileInfo wuri = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuid(uuid);
        //还未绑定角色
        if (wuri == null || wuri.getReptileId() == null){
            reptileUtil.getReptileUserId(reptileName,serverName);
            GetReptileId gri = new GetReptileId();
            gri.setId(1);
            gri = getReptileIdMapper.selectByPrimaryKey(gri);
            if (gri.getCode() != null && !StringUtils.isEmpty(gri)){
                System.out.println(gri.getCode());
                wuri = new WxUserReptileInfo();
                wuri.setReptileName(reptileName);
                wuri.setServerName(serverName);
                wuri.setUuid(uuid);
                wuri.setReptileId(gri.getCode());
                wuri.setServerId("dx1");
                wxUserReptileInfoMapper.insert(wuri);
                return true;
            }
        }
        //已绑定
        return false;
    }


    /**
     * serverame 转换成 serverId
     * @param serverName
     * @return
     */
    private String toServerId(String serverName){

        StringBuffer str = new StringBuffer();

        return str.toString();
    }

}
