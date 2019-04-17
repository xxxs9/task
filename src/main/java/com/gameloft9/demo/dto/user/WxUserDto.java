package com.gameloft9.demo.dto.user;

import lombok.Data;

@Data
public class WxUserDto {

    /**
     * 用户ID
     */
    private String uuid;
    /**
     * 爬取ID
     */
    private String reptileId;
    /**
     * 服务器ID
     */
    private String serverId;
}
