package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.UserComment;
import com.gameloft9.demo.dto.dynamic.UserCommentDto;
import com.gameloft9.demo.service.beans.system.CommentSelectResponse;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserCommentMapper extends Mapper<UserComment>{

    /**
     * 获取指定动态下的评论列表
     * @param dynamicId
     * @return
     */
    List<UserCommentDto> queryListByDynamicId(Integer dynamicId);

    /**
     * 查找所有
     * @param start
     * @param end
     * @param commentName
     * @return
     */
    List<CommentSelectResponse> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("commentName") String commentName);

    /**
     * 查找所有记录条目
     * @param commentName
     * @return
     */
    Integer countGetAll(@Param("commentName") String commentName);
}