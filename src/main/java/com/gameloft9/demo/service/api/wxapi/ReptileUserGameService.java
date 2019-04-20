package com.gameloft9.demo.service.api.wxapi;

public interface ReptileUserGameService {

    /**
     * 抓取用户游戏基本数据
     * @param serverId
     * @param reptileId
     */
    void getGameData(String uuid, String serverId, String reptileId);

    /**
     * 抓取用户近期比赛数据
     * @param serverId
     * @param reptileId
     */
    void getRecentMatch(String uuid, String serverId, String reptileId);
}
