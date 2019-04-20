package com.gameloft9.demo.service.api.wxapi;

import com.gameloft9.demo.dataaccess.model.user.ReptileUserRecentMatch;
import com.gameloft9.demo.dto.user.WxUserBaseGameDto;

import java.util.List;

public interface UserGameDataService {

    /**
     * 获取角色游戏数据
     * @param uuid
     */
    WxUserBaseGameDto getUserGameData(String uuid);

    /**
     * 获取近期比赛数据
     * @param uuid
     */
    List<ReptileUserRecentMatch> getUserRecentMatch(String uuid);
}
