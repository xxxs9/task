package com.gameloft9.demo.service.api.user;

import com.gameloft9.demo.dataaccess.model.user.UserComment;
import com.gameloft9.demo.service.beans.system.CommentSelectResponse;

import java.util.List;

public interface CommentService {

    List<CommentSelectResponse> getAll(String page, String limit, String commentName);

    Integer countGetAll(String commentName);

    Boolean deleteById(String id);

    UserComment getById(String id);

    Boolean updateComment(UserComment comment);
}
