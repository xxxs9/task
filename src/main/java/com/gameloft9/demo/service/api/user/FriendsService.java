package com.gameloft9.demo.service.api.user;

import com.gameloft9.demo.dataaccess.model.user.UserFriends;

import java.util.List;

public interface FriendsService {
    /**
     * 获取所有
     * @param page
     * @param limit
     * @param loginName
     * @return
     */
    List<UserFriends> getAll(String page, String limit, String loginName);

    /**
     * 获取条目数
     * @param loginName
     * @return
     */
    Integer countGetAll(String loginName);

    /**
     * 删除指定
     * @param id
     * @return
     */
    Boolean deleteById(String id);
}
