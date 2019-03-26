package com.gameloft9.demo.service.impl.user;

import com.gameloft9.demo.dataaccess.dao.user.UserCommentMapper;
import com.gameloft9.demo.dataaccess.model.user.UserComment;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.user.CommentService;
import com.gameloft9.demo.service.beans.system.CommentSelectResponse;
import com.gameloft9.demo.service.beans.system.PageRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired(required = false)
    UserCommentMapper dao;

    @Override
    public List<CommentSelectResponse> getAll(String page, String limit, String commentName) {
        PageRange pageRange = new PageRange(page,limit);
        return dao.getAll(pageRange.getStart(), pageRange.getEnd(), commentName);
    }

    @Override
    public Integer countGetAll(String commentName) {
        return dao.countGetAll(commentName);
    }

    @Override
    public Boolean deleteById(String id) {
        CheckUtil.notBlank(id, "id不能为空");

        dao.deleteByPrimaryKey(id);

        return true;
    }

    @Override
    public UserComment getById(String id) {
        CheckUtil.notBlank(id, "id不能为空");

        UserComment userComment = dao.selectByPrimaryKey(id);

        return userComment;
    }

    @Override
    public Boolean updateComment(UserComment comment) {
        //检测空字段
        CheckUtil.notNull(comment.getId(), "id不能为空");

        //获取旧信息==》时间字段不更新
        UserComment oldComment = dao.selectByPrimaryKey(comment.getId());

        UserComment rs = new UserComment();
        rs.setId(comment.getId());
        rs.setDynamicId(comment.getDynamicId());
        rs.setCommentDetails(comment.getCommentDetails());
        rs.setCommentName(comment.getCommentName());
        rs.setCreateTime(oldComment.getCreateTime());

        dao.updateByPrimaryKey(rs);
        return true;
    }
}
