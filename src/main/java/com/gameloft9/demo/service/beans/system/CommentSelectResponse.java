package com.gameloft9.demo.service.beans.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gameloft9.demo.dataaccess.model.user.UserDynamic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentSelectResponse implements Serializable {
    /**
     * 评论表
     */
    private Integer id;
    /**
     * 动态信息ID
     */
    private Integer dynamicId;
    /**
     * 评论者用户名
     */

    private String commentName;
    /**
     * 评论时间
     */
    @JsonFormat(timezone = "GTM+8",pattern = "yyyy-mm-dd HH:mm:ss")
    private Date createTime;

    /**
     * 评论内容
     */
    private String commentDetails;
    /**
     * 动态信息
     */
    private UserDynamic dynamic;
}
