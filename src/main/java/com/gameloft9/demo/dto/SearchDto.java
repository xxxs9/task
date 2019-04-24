package com.gameloft9.demo.dto;

import com.gameloft9.demo.dataaccess.model.user.ReptileUserBase;
import com.gameloft9.demo.dataaccess.model.user.ReptileUserContentStatistics;
import com.gameloft9.demo.dataaccess.model.user.ReptileUserRecentMatch;
import lombok.Data;

import java.util.List;

/**
 * 查询角色返回实体
 */
@Data
public class SearchDto {

    /**
     * 状态码
     */
    private int status;

    /**
     * 游戏角色基本信息
     */
    private ReptileUserBase reptileUserBase;

    /**
     * 游戏角色赛季统计数据
     */
    private List<ReptileUserContentStatistics> csList;

    /**
     * 游戏角色近期比赛数据
     */
    private List<ReptileUserRecentMatch> rmList;
}
