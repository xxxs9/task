package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.ReptileUserContentStatistics;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用户个赛季统计数据
 */
public interface ReptileUserContentStatisticsMapper extends Mapper<ReptileUserContentStatistics> {

    List<ReptileUserContentStatistics> queryListByReptileServerId(
            @Param("reptileId") String reptileId,
            @Param("serverId") String serverId);

    void insertList(List<ReptileUserContentStatistics> list);
}