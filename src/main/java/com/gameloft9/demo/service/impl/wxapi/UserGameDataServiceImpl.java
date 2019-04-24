package com.gameloft9.demo.service.impl.wxapi;

import com.gameloft9.demo.dataaccess.dao.user.*;
import com.gameloft9.demo.dataaccess.model.user.*;
import com.gameloft9.demo.dto.user.ReptileUserBaseDto;
import com.gameloft9.demo.dto.user.WxUserBaseGameDto;
import com.gameloft9.demo.service.api.wxapi.UserGameDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 显示用户游戏数据逻辑
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserGameDataServiceImpl implements UserGameDataService {

    @Autowired
    private WxUserReptileInfoMapper wxUserReptileInfoMapper;

    @Autowired
    private ReptileUserRecentMatchMapper reptileUserRecentMatchMapper;
    @Autowired
    private ReptileUserBaseMapper reptileUserBaseMapper;
    @Autowired
    private ReptileUserContentStatisticsMapper reptileUserContentStatisticsMapper;

    @Autowired
    private WxUserMapper wxUserMapper;

    /**
     * 获取用户游戏的基本数据
     * 包括赛季统计数据
     * @param uuid
     */
    @Override
    public WxUserBaseGameDto getUserGameData(String uuid) {
        WxUserBaseGameDto dto = new WxUserBaseGameDto();
        WxUserReptileInfo info = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuid(uuid);
        WxUser wxUser = wxUserMapper.queryWxUserByUuid(uuid);
        if (info != null && !StringUtils.isEmpty(info.getServerId())
            && !StringUtils.isEmpty(info.getReptileId())) {
            //获取用户游戏基本数据
            ReptileUserBase uBase = reptileUserBaseMapper.queryByReptileServerId(
                    info.getServerId(), info.getReptileId());
            //获取近期比赛数据
            List<ReptileUserContentStatistics> csList = reptileUserContentStatisticsMapper.queryListByReptileServerId(
                    info.getReptileId(),info.getServerId());
            dto.setCsList(csList);
            ReptileUserBaseDto bDto = new ReptileUserBaseDto();
            BeanUtils.copyProperties(uBase,bDto);
            bDto.setAvatarUrl(wxUser.getAvatarUrl());
            bDto.setGender(wxUser.getGender());
            bDto.setNickname(wxUser.getNickname());
            dto.setUBase(bDto);
        }
        return dto;
    }

    /**
     * 获取近期比赛数据
     * @param uuid
     */
    @Override
    public List<ReptileUserRecentMatch> getUserRecentMatch(String uuid) {
        WxUserReptileInfo info = wxUserReptileInfoMapper.queryWxUserReptileInfoByUuid(uuid);
        if (info != null && !StringUtils.isEmpty(info.getServerId())
                && !StringUtils.isEmpty(info.getReptileId())) {
            return reptileUserRecentMatchMapper.queryListByReptileServerId(info.getReptileId(),info.getServerId());

        }
        return new ArrayList<ReptileUserRecentMatch>();
    }
}
