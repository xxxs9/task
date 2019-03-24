package com.gameloft9.demo.service.api.user;

import com.gameloft9.demo.dataaccess.model.user.UserDynamic;

import java.util.List;

public interface DynamicService {


    /**
     * 获取所有记录
     * @param page
     * @param limit
     * @param loginName
     * @return
     */
    List<UserDynamic> getAll(String page, String limit, String loginName);

    /**
     * 获取所有条目
     * @param loginName
     * @return
     */
    Integer countGetAll(String loginName);

    /**
     * 删除指定信息
     * @param id
     * @return
     */
    Boolean deleteById(String id);

    /**
     * 获取指定的动态信息
     * @param id
     * @return
     */
    UserDynamic getById(String id);

    /**
     * 更新动态信息
     * @param dynamic
     * @return
     */
    Boolean updateDynamic(UserDynamic dynamic);
}
