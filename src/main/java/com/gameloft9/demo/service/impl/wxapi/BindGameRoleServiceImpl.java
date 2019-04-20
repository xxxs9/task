package com.gameloft9.demo.service.impl.wxapi;

import com.gameloft9.demo.dataaccess.dao.user.GetReptileIdMapper;
import com.gameloft9.demo.dataaccess.dao.user.WxUserReptileInfoMapper;
import com.gameloft9.demo.dataaccess.model.user.GetReptileId;
import com.gameloft9.demo.dataaccess.model.user.WxUserReptileInfo;
import com.gameloft9.demo.dto.user.WxUserDto;
import com.gameloft9.demo.service.api.wxapi.BindGameRoleService;
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

        WxUserReptileInfo wuri = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuid(uuid);
        //还未绑定角色
        if (wuri == null || StringUtils.isEmpty(wuri.getReptileId())){
            reptileUtil.getReptileUserId(reptileName,serverName);

            Timer timer = new Timer();// 实例化Timer类
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
                            wuri.setServerId(toServerId(serverName));
                            wxUserReptileInfoMapper.insert(wuri);
                            gri.setCode("");
                            getReptileIdMapper.updateByPrimaryKey(gri);
                        }else{
                            wuri.setReptileName(reptileName);
                            wuri.setServerName(serverName);
                            wuri.setReptileId(gri.getCode());
                            wuri.setServerId(toServerId(serverName));
                            wxUserReptileInfoMapper.updateByPrimaryKey(wuri);
                        }
                    }
                    System.out.println("退出");
                    this.cancel();
                }
            }, 2000);// 这里百毫秒
            return true;
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
        String ser = serverName.substring(0,2);
        String id = serverName.substring(2,serverName.length());
        switch (ser){
            case "电信":str.append("dx");break;
            case "网通":str.append("wt");break;
            case "教育":str.append("jy");break;
            case "全网":str.append("qw");break;
            default: break;
        }
        if (id.length() == 2){
            str.append("1");
            id = id.substring(1,2);
        }
        if (id.length() == 1){
            switch (id){
                case "一": str.append("1");break;
                case "二": str.append("2");break;
                case "三": str.append("3");break;
                case "四": str.append("4");break;
                case "五": str.append("5");break;
                case "六": str.append("6");break;
                case "七": str.append("7");break;
                case "八": str.append("8");break;
                case "九": str.append("9");break;
                default:break;
            }
        }
        return str.toString();
    }

}
