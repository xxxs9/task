package com.gameloft9.demo.service.api.user;

import com.gameloft9.demo.dataaccess.model.user.UserInformation;

import java.util.List;

/**
 * 关于资讯的业务接口
 */
public interface InformtionService {
    /**
     * 分页获取用户列表
     *
     * @param page         页序
     * @param limit        分页大小
     * @param loginName     发布者名
     * @param informationTitle     文章标题
     * @param isTop       状态 0-不置顶 1-置顶
     */
    List<UserInformation> getAll(String page, String limit, String loginName, String informationTitle, String isTop);

    /**
     * 获取用户列表大小
     *
     * @param loginName     登录名
     * @param informationTitle     文章标题
     * @param isTop       状态 0-不置顶 1-置顶
     */
    int countGetAll(String loginName, String informationTitle, String isTop);

    /**
     * 根据资讯ID删除用户
     * @param id 用户id
     * */
    Boolean deleteById(String id);

    /**
     * 根据id获取记录
     * @param id 主键
     * */
//    SysUserResponse getById(String id);

    /**
     * 更新
     * @param user 记录
     * */
//    Boolean updateUser(UserUpdateRequest user);
}
