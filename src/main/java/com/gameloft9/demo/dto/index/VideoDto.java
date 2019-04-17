package com.gameloft9.demo.dto.index;

import lombok.Data;

@Data
public class VideoDto {

    /**
     * 视频标题
     */
    private String title;
    /**
     * 视频地址
     */
    private String url;
    /**
     * 视频封面
     */
    private String img;
    /**
     * 播放数
     */
    private String num;
    /**
     * 播放时间
     */
    private String count;

}
