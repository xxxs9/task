package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.ReptileUserRecentMatch;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用户近期比赛数据
 */
public interface ReptileUserRecentMatchMapper extends Mapper<ReptileUserRecentMatch> {

    /**
     * 查询制定用户的比赛记录
     * @param reptileId
     * @param serverId
     * @return
     */
    List<ReptileUserRecentMatch> queryListByReptileServerId(
            @Param("reptileId") String reptileId,
            @Param("serverId") String serverId
    );

    /**
     * 批量插入爬取的比赛信息
     * @param list
     */
    void insertList(List<ReptileUserRecentMatch> list);
}