package com.gameloft9.demo.service.beans.system;

import lombok.Data;

import java.io.Serializable;
@Data
public class InformationAddRequest implements Serializable {

    //发布者
    private String loginName;
    //标题
    private String informationTitle;
    //是否置顶
    private Integer isTop;
    //资讯封面
    private String informationImg;
    //资讯内容
    private String informationContent;
    //信息类型
    private Integer informationType;
}
