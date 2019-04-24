package com.gameloft9.demo.service.impl.user;

import com.gameloft9.demo.dataaccess.dao.user.WxUserMapper;
import com.gameloft9.demo.dataaccess.model.user.WxUser;
import com.gameloft9.demo.service.api.user.WxUserManageService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxUserManageServiceImpl implements WxUserManageService {

    @Autowired
    private WxUserMapper dao;

    /**
     * 获取微信用户分页数据
     * @param page
     * @param limit
     * @param username
     * @return
     */
    @Override
    public List<WxUser> getAll(String page, String limit, String username) {
        PageRange pageRange = new PageRange(page,limit);
        return dao.getAll(pageRange.getStart(), pageRange.getEnd(),username);
    }

    /**
     * 获取数据总条目数
     * @param username
     * @return
     */
    @Override
    public Integer countGetAll(String username) {
        return dao.countGetAll(username);
    }
}
