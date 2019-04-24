package com.gameloft9.demo.service.api.wxapi;

import com.gameloft9.demo.dto.SearchDto;

public interface SearchService {

//    SearchDto

    /**
     * 搜索角色
     * @param roleName
     * @param serverName
     * @return
     */
    SearchDto searchRole(String roleName, String serverName);

//    /**
//     * 获取角色数据
//     * @param roleName
//     * @param serverName
//     * @return
//     */
//    SearchDto getRoleData(String roleName, String serverName);

}
