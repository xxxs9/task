package com.gameloft9.demo.dto.dynamic;

import com.gameloft9.demo.dataaccess.model.user.UserComment;
import com.gameloft9.demo.dataaccess.model.user.UserDynamic;
import lombok.Data;

import java.util.List;

/**
 * 好友动态返回实体
 */
@Data
public class DynamicDto {

    private UserDynamicDto userDynamic;

    private List<UserCommentDto> ucList;
}
