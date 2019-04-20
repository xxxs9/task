package com.gameloft9.demo.service.impl;

import com.gameloft9.demo.dataaccess.dao.user.ReptileUserBaseMapper;
import com.gameloft9.demo.dataaccess.dao.user.WxUserReptileInfoMapper;
import com.gameloft9.demo.dataaccess.model.user.ReptileUserBase;
import com.gameloft9.demo.dataaccess.model.user.WxUserReptileInfo;
import com.gameloft9.demo.service.api.wxapi.ReptileUserGameService;
import com.gameloft9.demo.webmagic.ReptileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReptileUserGameServiceImpl implements ReptileUserGameService {

    @Autowired
    private ReptileUtil reptileUtil;
    @Autowired
    private WxUserReptileInfoMapper wxUserReptileInfoMapper;
    @Autowired
    private ReptileUserBaseMapper reptileUserBaseMapper;
//    @Autowired
//    private ReptileUserContentStatisticsMapper reptileUserContentStatisticsMapper;
//    @Autowired
//    private ReptileUserRecentMatchMapper reptileUserRecentMatchMapper;


    /**
     * 抓取用户游戏基本信息
     * @param uuid
     * @param serverId
     * @param reptileId
     */
    @Override
    public void getGameData(String uuid, String serverId, String reptileId) {
        //判断用户绑定信息是否合法
        WxUserReptileInfo wi = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuid(uuid);
        if (wi != null && wi.getServerId().equals(serverId) && wi.getReptileId().equals(reptileId)){
            ReptileUserBase rub = reptileUserBaseMapper.queryByReptileServerId(serverId,reptileId);
            if (rub == null){
                reptileUtil.getGameData(reptileId,serverId);
            }
        }
    }

    /**
     * 抓取用户最近比赛记录
     * 如果是重复的比赛会入库失败
     * 通过索引UQ限制每条比赛必须为唯一
     * @param uuid
     * @param serverId
     * @param reptileId
     */
    @Override
    public void getRecentMatch(String uuid, String serverId, String reptileId) {
        WxUserReptileInfo wi = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuid(uuid);
        if (wi != null && wi.getServerId().equals(serverId) && wi.getReptileId().equals(reptileId)){
            reptileUtil.getRecentMatch(reptileId,serverId);
        }
    }
}
