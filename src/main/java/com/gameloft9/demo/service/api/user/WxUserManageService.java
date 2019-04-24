package com.gameloft9.demo.service.api.user;

import com.gameloft9.demo.dataaccess.model.user.WxUser;

import java.util.List;

/**
 * 微信用户业务逻辑接口
 */
public interface WxUserManageService {

    /**
     * 分页加载所有用户
     * @param page
     * @param limit
     * @param username
     * @return
     */
    List<WxUser> getAll(String page, String limit, String username);

    /**
     * 获取条目数
     * @param username
     * @return
     */
    Integer countGetAll(String username);
}
