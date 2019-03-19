package com.gameloft9.demo.dataaccess.model.user;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_comment")
public class UserComment {
    /**
     * 评论表
     */
    @Column(name = "ID")
    private Integer id;

    /**
     * 朋友圈动态关联ID
     */
    @Column(name = "DYNAMIC_ID")
    private Integer dynamicId;

    /**
     * 评论者用户名
     */
    @Column(name = "COMMENT_NAME")
    private String commentName;

    /**
     * 评论时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 评论内容
     */
    @Column(name = "COMMENT_DETAILS")
    private String commentDetails;

    /**
     * 获取评论表
     *
     * @return ID - 评论表
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置评论表
     *
     * @param id 评论表
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取朋友圈动态关联ID
     *
     * @return DYNAMIC_ID - 朋友圈动态关联ID
     */
    public Integer getDynamicId() {
        return dynamicId;
    }

    /**
     * 设置朋友圈动态关联ID
     *
     * @param dynamicId 朋友圈动态关联ID
     */
    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    /**
     * 获取评论者用户名
     *
     * @return COMMENT_NAME - 评论者用户名
     */
    public String getCommentName() {
        return commentName;
    }

    /**
     * 设置评论者用户名
     *
     * @param commentName 评论者用户名
     */
    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    /**
     * 获取评论时间
     *
     * @return CREATE_TIME - 评论时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置评论时间
     *
     * @param createTime 评论时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取评论内容
     *
     * @return COMMENT_DETAILS - 评论内容
     */
    public String getCommentDetails() {
        return commentDetails;
    }

    /**
     * 设置评论内容
     *
     * @param commentDetails 评论内容
     */
    public void setCommentDetails(String commentDetails) {
        this.commentDetails = commentDetails;
    }
}