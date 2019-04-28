package com.gameloft9.demo.dto.dynamic;

import com.gameloft9.demo.dataaccess.model.user.UserDynamic;
import com.gameloft9.demo.webmagic.template.ReptileDataUtil;
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

    public void setNickname(String nickname){
        this.nickname = ReptileDataUtil.unicodeToString(nickname);
    }
}
