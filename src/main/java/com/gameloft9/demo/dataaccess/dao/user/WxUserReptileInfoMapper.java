package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.WxUserReptileInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface WxUserReptileInfoMapper extends Mapper<WxUserReptileInfo> {


    WxUserReptileInfo queryWxUserReptileInfoByUuidReptilServerName(
            @Param("reptileName") String reptileName,
            @Param("serverName") String serverName
    );

    /**
     * 通过uuid获取抓取的信息
     * @param uuid
     * @return
     */
    WxUserReptileInfo queryWxUserReptileInfoByUuid(String uuid);
}