package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.HeroBase;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HeroBaseMapper extends Mapper<HeroBase> {

    List<HeroBase> getListByPageNumSize(
            @Param("pageNum") Integer pageNum,
            @Param("pageSize") Integer pageSize);

    List<HeroBase> getNewList();
}