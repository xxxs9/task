package com.gameloft9.demo.service.impl.wxapi;

import com.gameloft9.demo.dataaccess.dao.user.GetReptileIdMapper;
import com.gameloft9.demo.dataaccess.dao.user.WxUserReptileInfoMapper;
import com.gameloft9.demo.dataaccess.model.user.GetReptileId;
import com.gameloft9.demo.dataaccess.model.user.WxUserReptileInfo;
import com.gameloft9.demo.dto.user.WxUserDto;
import com.gameloft9.demo.service.api.wxapi.BindGameRoleService;
import com.gameloft9.demo.webmagic.ReptileDataUtil;
import com.gameloft9.demo.webmagic.ReptileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Timer;
import java.util.TimerTask;


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

    @Override
    public WxUserDto getBase(String uuid) {
        WxUserReptileInfo wri = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuid(uuid);
        WxUserDto dto = new WxUserDto();
        if (wri != null){
            dto.setUuid(uuid);
            dto.setServerId(wri.getServerId());
            dto.setReptileId(wri.getReptileId());
        }
        return dto;
    }

    /**
     * 绑定角色信息
     * @param reptileName
     * @param serverName
     * @return
     */
    @Override
    public Boolean bindGameRole(String uuid ,String reptileName, String serverName) {
        WxUserReptileInfo wurir = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuidReptilServerName(reptileName,serverName);
        WxUserReptileInfo wuri = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuid(uuid);
        if (wurir != null && wuri == null){
            //存在角色但没有绑定用户 - 直接更新用户
            if (wurir.getUuid() == null || wurir.getUuid().length() == 0){
                wurir.setUuid(uuid);
                wxUserReptileInfoMapper.updateByPrimaryKey(wurir);
                return true;
            }else{
                //角色存在并且已经绑定 - 返回错误
                return false;
            }
        }
        //还未绑定角色
        if (wuri == null || StringUtils.isEmpty(wuri.getReptileId())){
            reptileUtil.getReptileUserId(reptileName,serverName);
            // 实例化Timer类
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    WxUserReptileInfo wuri = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuid(uuid);
                    GetReptileId gri = new GetReptileId();
                    gri.setId(1);
                    gri = getReptileIdMapper.selectByPrimaryKey(gri);
                    if (gri.getCode() != null && gri.getCode().length() > 0){
                        if (wuri == null) {
                            System.out.println(gri.getCode());
                            wuri = new WxUserReptileInfo();
                            wuri.setReptileName(reptileName);
                            wuri.setServerName(serverName);
                            wuri.setUuid(uuid);
                            wuri.setReptileId(gri.getCode());
                            wuri.setServerId(ReptileDataUtil.toServerId(serverName));
                            wxUserReptileInfoMapper.insert(wuri);
                        }else{
                            wuri.setReptileName(reptileName);
                            wuri.setServerName(serverName);
                            wuri.setReptileId(gri.getCode());
                            wuri.setServerId(ReptileDataUtil.toServerId(serverName));
                            wxUserReptileInfoMapper.updateByPrimaryKey(wuri);
                        }
                        gri.setCode("");
                        getReptileIdMapper.updateByPrimaryKey(gri);
                        //抓取用户游戏数据
                        reptileUtil.getGameData(wuri.getReptileId(),wuri.getServerId());
                        reptileUtil.getRecentMatch(wuri.getReptileId(),wuri.getServerId());
                    }
                    System.out.println("退出");
                    this.cancel();
                    // 这里百毫秒
                }
            }, 2000);
            return true;
        }
        //已绑定
        return false;
    }

}
