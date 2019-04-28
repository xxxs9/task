package com.gameloft9.demo.dto.dynamic;

import com.gameloft9.demo.dataaccess.model.user.UserComment;
import com.gameloft9.demo.webmagic.template.ReptileDataUtil;
import lombok.Data;

@Data
public class UserCommentDto extends UserComment {
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
        super.setCommentName(ReptileDataUtil.unicodeToString(super.getCommentName()));
    }



}
