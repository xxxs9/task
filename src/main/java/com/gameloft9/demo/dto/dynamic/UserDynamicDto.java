package com.gameloft9.demo.dto.dynamic;

import com.gameloft9.demo.dataaccess.model.user.UserDynamic;
import lombok.Data;

@Data
public class UserDynamicDto extends UserDynamic {
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatarUrl;
}
