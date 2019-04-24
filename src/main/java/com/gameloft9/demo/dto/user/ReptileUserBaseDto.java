package com.gameloft9.demo.dto.user;

import com.gameloft9.demo.dataaccess.model.user.ReptileUserBase;
import lombok.Data;

@Data
public class ReptileUserBaseDto extends ReptileUserBase {
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 性别
     */
    private String gender;
}
