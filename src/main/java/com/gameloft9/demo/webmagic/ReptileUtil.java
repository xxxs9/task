package com.gameloft9.demo.webmagic;

import com.gameloft9.demo.webmagic.pipeline.*;
import com.gameloft9.demo.webmagic.processor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.security.PrivateKey;

/**
 * 爬虫工具类
 */
@Component
public class ReptileUtil {

    /**
     * 获取多玩盒子周免
     */
    private String heroWeeKFreeUrl = "http://api.lolbox.duowan.com/";
    /**
     * 爬取用户id地址
     */
    private String uri = "http://api.lolbox.duowan.com/api/v3/player/search/?";
    /**
     * 爬取用户游戏数据 + serverId/reptileId
     */
    private String dataUri = "http://api.lolbox.duowan.com/player";
    /**
     * 爬取用户最近比赛数据 + serverId/reptileId/recent_games/
     */
    private String matchUri = "http://api.lolbox.duowan.com/player";
    /**
     * 爬取英雄数据
     */
    private String heroUri = "http://lol.duowan.com/hero";

    /**
     * 爬取英雄出装Url
     */
    private String heroEquipUrl = "http://db.duowan.com/lolnewcz/article?record_id=";

    @Autowired
    private LolUserPipeline lolUserPipeline;
    @Autowired
    private GameDataPipeline gameDataPipeline;
    @Autowired
    private RecentMatchPipeline recentMatchPipeline;

    @Autowired
    private LolHeroPipeline lolHeroPipeline;
    @Autowired
    private LolHeroDetailPipeline lolHeroDetailPipeline;
    @Autowired
    private HeroEquipPipeline heroEquipPipeline;
    @Autowired
    private HeroWeeKFreePipeline heroWeeKFreePipeline;


    /**
     * 获取周免英雄数据
     */
    public void getHeroWeekFree(){
        StringBuffer url = new StringBuffer(heroWeeKFreeUrl);

        //初始化爬虫任务
        HeroWeeKFreeProcessor hwfp = new HeroWeeKFreeProcessor();
        Spider spider = Spider.create(hwfp);

        //检测爬虫任务
        long startTime, endTime;
        System.out.println("开始爬取英雄周免数据...");
        startTime = System.currentTimeMillis();
        spider.addUrl(url.toString()).addPipeline(heroWeeKFreePipeline).thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("英雄周免数据爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }



    /**
     * 获取英雄装备信息，返回的是json数据
     */
    public void getHeroEquipData(String equipId){
        StringBuffer url = new StringBuffer(heroEquipUrl).append(equipId);

        //初始化爬虫任务
        HeroEquipProcessor hep = new HeroEquipProcessor();
        Spider spider = Spider.create(hep);

        //检测爬虫任务
        long startTime, endTime;
        System.out.println("开始爬取英雄出装数据...");
        startTime = System.currentTimeMillis();
        spider.addUrl(url.toString()).addPipeline(heroEquipPipeline).thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("英雄出装数据爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }

    /**
     * 获取lol英雄基本数据
     */
    public void getLolHeroData(){
        StringBuffer url = new StringBuffer(heroUri);

        //初始化爬虫任务
        LolHeroProcessor lhp = new LolHeroProcessor();
        Spider spider = Spider.create(lhp);

        //检测爬虫任务
        long startTime, endTime;
        System.out.println("开始爬取英雄基本数据...");
        startTime = System.currentTimeMillis();
        spider.addUrl(url.toString()).addPipeline(lolHeroPipeline).thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("英雄基本数据爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }

    /**
     * 获取lol英雄详细数据
     */
    public void getLolHeroDetail(String url){
        //初始化爬虫任务
        LolHeroDetailProcessor lhdp = new LolHeroDetailProcessor();
        Spider spider = Spider.create(lhdp);

        //检测爬虫任务
        long startTime, endTime;
        System.out.println("开始爬取英雄详细数据...");
        startTime = System.currentTimeMillis();
        spider.addUrl(url).addPipeline(lolHeroDetailPipeline).thread(1).run();
        endTime = System.currentTimeMillis();
        System.out.println("英雄详细数据爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }


    //---------- User Data ----------

    /**
     * 获取爬取的用户id
     * @param username
     * @param serverStr
     */
    public void getReptileUserId(String username, String serverStr){
        //拼接url
        StringBuffer url = new StringBuffer(uri);
        url.append("player_name_list=").append(username);
        url.append("&game_zone=").append(serverStr);

        //初始化爬虫任务
        LolUserIdProcessor lup = new LolUserIdProcessor();
        Spider spider = Spider.create(lup);

        //检测爬虫任务
        long startTime, endTime;
        System.out.println("开始爬取用户ID...");
        startTime = System.currentTimeMillis();
        spider.addUrl(url.toString()).addPipeline(lolUserPipeline).thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("用户ID爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }

    /**
     * 爬取用户游戏数据
     * @param reptileId
     * @param serverId
     */
    public void getGameData(String reptileId, String serverId){
        //拼接url
        StringBuffer url = new StringBuffer(dataUri);
        url.append("/").append(serverId);
        url.append("/").append(reptileId);

        //初始化爬虫任务
        GameDateProcessor dp = new GameDateProcessor();
        Spider spider = Spider.create(dp);

        //检测爬虫任务
        long startTime, endTime;
        System.out.println("开始爬取用户游戏数据...");
        startTime = System.currentTimeMillis();
        spider.addUrl(url.toString()).addPipeline(gameDataPipeline).thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("用户游戏数据爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }

    /**
     *  获取用户近期比赛数据
     * @param reptileId
     * @param serverId
     */
    public void getRecentMatch(String reptileId, String serverId){
        //拼接url
        StringBuffer url = new StringBuffer(matchUri);
        url.append("/").append(serverId);
        url.append("/").append(reptileId);
        url.append("/recent_games");

        //初始化爬虫任务
        RecentMatchProcessor rmp = new RecentMatchProcessor();
        Spider spider = Spider.create(rmp);

        //检测爬虫任务
        long startTime, endTime;
        System.out.println("开始爬取用户近期比赛数据...");
        startTime = System.currentTimeMillis();
        spider.addUrl(url.toString()).addPipeline(recentMatchPipeline).thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("用户近期比赛数据爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }
}
