package com.gameloft9.demo.service.api.wxapi;

public interface UserQueryService {

    /**
     * 通过uuid校验该用户是否存在
     * true存在
     * false不存在
     * @param uuid
     * @return
     */
    boolean queryWxUser(String uuid);
}
