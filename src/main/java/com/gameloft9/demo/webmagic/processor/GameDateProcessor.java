package com.gameloft9.demo.webmagic.processor;

import com.gameloft9.demo.dataaccess.model.user.ReptileUserBase;
import com.gameloft9.demo.dataaccess.model.user.ReptileUserContentStatistics;
import com.gameloft9.demo.webmagic.beans.UserGame;
import com.gameloft9.demo.webmagic.template.ExtractTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * 抓取用户英雄联盟统计数据
 */
public class GameDateProcessor implements PageProcessor {

    /**
     * 抓取网站的相关配置，可以包括编码、抓取间隔1s、重试次数等
     */
    private Site site = Site.me().setCharset("utf8").setRetryTimes(1000).setSleepTime(1000);

    @Override
    public void process(Page page) {
        Html html = page.getHtml();

        //获取数据库匹配的数据
        Selectable u = html.xpath("//p[@class=\"central\"]");
        String url =  u.nodes().get(0).xpath("//p[@class=\"central\"]/a/@href").get();
        String[] s = url.split("/");
        String serverId = s[2];
        String reptilrId = s[3];

        //基本统计数据
        String content = html.xpath("//table[@class=\"table\"]/tbody/tr/td/allText()").get();
        s = content.split(" ");

        ReptileUserBase rub = new ReptileUserBase();
        rub.setServerId(serverId);
        rub.setReptileId(reptilrId);
        //战斗力
        String a = s[1].replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
        rub.setBatleAbility(Integer.valueOf(a));
        //称赞
        a = s[3].replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
        rub.setPraise(Integer.valueOf(a));
        //贬低
        a = s[5].replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
        rub.setBelttle(Integer.valueOf(a));
        //等级
        rub.setLevel(s[6]);

        //各个赛季统计数据
        Selectable st =  html.xpath("//table[@class=\"table table-hover recent-game-list\"]");
        List<Selectable> stList = st.nodes();
        List<ReptileUserContentStatistics> rList = ExtractTemplate.extractContentStatisticsTemplat(serverId,reptilrId,stList.get(0));

//        if (stList.size() > 2){
//            //英雄使用情况
//            ExtractTemplate.extractHeroDetailTemplat(stList.get(1));
//        }

        UserGame ug = new UserGame();
        ug.setRub(rub);
        ug.setRList(rList);
        page.putField("ug",ug);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
