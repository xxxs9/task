package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.ReptileUserBase;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * 用户游戏基本数据
 */
public interface ReptileUserBaseMapper extends Mapper<ReptileUserBase> {

    /**
     * 获取用户游戏基本数据
     * @param serverId
     * @param reptileId
     * @return
     */
    ReptileUserBase queryByReptileServerId(
            @Param("serverId") String serverId,
            @Param("reptileId") String reptileId);
}