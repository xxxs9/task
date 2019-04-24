package com.gameloft9.demo.dto.user;

import com.gameloft9.demo.dataaccess.model.user.ReptileUserBase;
import com.gameloft9.demo.dataaccess.model.user.ReptileUserContentStatistics;
import lombok.Data;

import java.util.List;

/**
 * 用户游戏基本数据返回实体
 */
@Data
public class WxUserBaseGameDto {

    /**
     * 基本数据
     */
    private ReptileUserBaseDto uBase;

    /**
     * 用户赛季统计结果
     */
    private List<ReptileUserContentStatistics> csList;
}
