package com.gameloft9.demo.service.impl.wxapi;

import com.gameloft9.demo.dataaccess.dao.user.*;
import com.gameloft9.demo.dataaccess.model.user.GetReptileId;
import com.gameloft9.demo.dataaccess.model.user.WxUserReptileInfo;
import com.gameloft9.demo.dto.SearchDto;
import com.gameloft9.demo.service.api.wxapi.SearchService;
import com.gameloft9.demo.webmagic.ReptileUtil;
import com.gameloft9.demo.webmagic.template.ReptileDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 搜索功能的业务逻辑
 */
@Service
public class SearchServiceImpl implements SearchService {

    //爬取信息基本支持注入
    @Autowired
    private WxUserReptileInfoMapper wxUserReptileInfoMapper;

    //爬取数据相关注入
    @Autowired
    private ReptileUtil reptileUtil;
    @Autowired
    private GetReptileIdMapper getReptileIdMapper;

    //角色相关数据注入
    //用户游戏基本信息
    @Autowired
    private ReptileUserBaseMapper reptileUserBaseMapper;
    //用户各赛季统计表
    @Autowired
    private ReptileUserContentStatisticsMapper reptileUserContentStatisticsMapper;
    //用户的近期比赛数据
    @Autowired
    private ReptileUserRecentMatchMapper reptileUserRecentMatchMapper;

    /**
     * 查询该角色
     * 有 - 数据库获取数据
     * 无 - 开始爬取任务，并返回等待
     * @param roleName
     * @param serverName
     * @return
     */
    @Override
    public SearchDto searchRole(String roleName, String serverName) {
        SearchDto dto = new SearchDto();
        //先搜索wx_user_reptile_info
        WxUserReptileInfo wuri = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuidReptilServerName(roleName,serverName);
        //有该条目
        if (wuri != null){
            //角色对应的爬取id存在
            if (wuri.getReptileId() != null && wuri.getReptileId().length() > 0){
                dto = getRoleData(roleName,serverName);
                dto.setStatus(200);
                return dto;
            }else{
                //角色的爬取Id不存在 - 可能是没有获取到
                reptile(roleName,serverName);
            }
        }else{
            //创建新条目，并查询爬取id
            reptile(roleName,serverName);
        }
        dto.setStatus(201);
        return dto;
    }

    /**
     * 返回游戏角色数据
     * @param roleName
     * @param serverName
     * @return
     */
    private SearchDto getRoleData(String roleName, String serverName){
        SearchDto sd = new SearchDto();
        WxUserReptileInfo wuri = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuidReptilServerName(roleName,serverName);
        if (wuri != null && wuri.getReptileId() != null && wuri.getReptileId().length() > 0){
            sd.setReptileUserBase(reptileUserBaseMapper.queryByReptileServerId(wuri.getServerId(),wuri.getReptileId()));
            sd.setCsList(reptileUserContentStatisticsMapper.queryListByReptileServerId(wuri.getReptileId(),wuri.getServerId()));
            sd.setRmList(reptileUserRecentMatchMapper.queryListByReptileServerId(wuri.getReptileId(),wuri.getServerId()));
        }
        return sd;
    }

    /**
     * 尝试爬取爬虫id
     * 创建的条目没有uuid -- 注意角色绑定
     * 更新的条目可能有uuid
     * @param reptileName
     * @param serverName
     */
    private void reptile(String reptileName,String serverName){

        reptileUtil.getReptileUserId(reptileName,serverName);
        // 实例化Timer类
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                WxUserReptileInfo wuri = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuidReptilServerName(reptileName,serverName);
                GetReptileId gri = new GetReptileId();
                gri.setId(1);
                gri = getReptileIdMapper.selectByPrimaryKey(gri);
                if (gri.getCode() != null && gri.getCode().length() > 0){
                    if (wuri == null) {
                        System.out.println(gri.getCode());
                        wuri = new WxUserReptileInfo();
                        wuri.setReptileName(reptileName);
                        wuri.setServerName(serverName);
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
                    reptileUtil.getGameData(wuri.getReptileId(), wuri.getServerId());
                    reptileUtil.getRecentMatch(wuri.getReptileId(), wuri.getServerId());
                }
                System.out.println("退出");
                this.cancel();
                // 这里百毫秒
            }
        }, 2000);
    }
}
