package com.gameloft9.demo.webmagic.processor;

import com.gameloft9.demo.dataaccess.model.user.HeroDetail;
import com.gameloft9.demo.dataaccess.model.user.HeroSkill;
import com.gameloft9.demo.webmagic.HeroReptile;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 抓取英雄明细的爬虫
 */
public class LolHeroDetailProcessor implements PageProcessor {

    /**
     * 抓取网站的相关配置，可以包括编码、抓取间隔1s、重试次数等
     */
    private Site site = Site.me().setCharset("utf8").setRetryTimes(1000).setSleepTime(1000);

    @Override
    public void process(Page page) {
        Html html = page.getHtml();

        String heroName = html.xpath("//h1[@class=\"hero-name\"]/text()").get().trim();
        String heroTitle = html.xpath("//h2[@class=\"hero-title\"]/text()").get().trim();
        //定位标签
        String output = html.xpath("//div[@class=\"hero-position\"]/allText()").get();
        //能力值
        List<Selectable> sList = html.xpath("//div[@class=\"hero-ability\"]/span").nodes();

        String existence = strDel(sList.get(0).xpath("//i/@style").get());
        String physics = strDel(sList.get(1).xpath("//i/@style").get());
        String magic = strDel(sList.get(2).xpath("//i/@style").get());
        String operation = strDel(sList.get(3).xpath("//i/@style").get());


        //英雄背景图片
        String  backgroundImg = html.xpath("//li[@class=\"ui-slide__panel is-active\"]/img/@src").get();
        //英雄点券
        Selectable selectable =  html.xpath("//div[@class=\"hero-box ext-attr\"]/div[@class=\"hero-box__bd\"]/ul/li").nodes().get(0);
        String mony = selectable.xpath("//li/span/text()").get().trim();
        String[] monys = mony.split("/");
        String goldCoin = monys[0];
        String ticket = monys[1];
        //英雄详情实体
        HeroDetail hd = new HeroDetail();

        hd.setBackgroundImg(backgroundImg);
        hd.setGoldCoin(goldCoin);
        hd.setTicket(ticket);

        hd.setHeroTitle(heroTitle);
        hd.setHeroName(heroName);
        hd.setOutput(output);
        hd.setExistence(Integer.valueOf(existence));
        hd.setPhysics(Integer.valueOf(physics));
        hd.setMagic(Integer.valueOf(magic));
        hd.setOperation(Integer.valueOf(operation));

        //技能
        Selectable skill = html.xpath("//div[@class=\"ZQ-773\"]//div[@class=\"mod-tab-content\"]");
        //图标列表
        List<Selectable> skillList = skill.xpath("//ul/li").nodes();
        //描述列表
        List<Selectable> sdesList = skill.xpath("//div[@class=\"mod-tab-content\"]/div").nodes();
        //技能详情实体
        List<HeroSkill> hsList = new ArrayList<>();
        if (skillList != null && skillList.size() > 0 && skillList.size() == sdesList.size()){
            for (int i = 0 , num = skillList.size(); i < num ; i ++ ){
                HeroSkill hs = new HeroSkill();
                hs.setHeroName(heroName);
                hs.setSkillImgUrl(skillList.get(i).xpath("//img/@src").get());
                hs.setSkillName(sdesList.get(i).xpath("//span[@class=\"name\"]/text()").get());
                hs.setSkillKey(sdesList.get(i).xpath("//span[@class=\"hot-key\"]/text()").get());
                hs.setSkillDescribe(htmlToString(sdesList.get(i).xpath("//div[@class=\"content-bd\"]/html()").get()));
                hsList.add(hs);
            }
        }
        HeroReptile hr = new HeroReptile();
        hr.setHeroDetail(hd);
        hr.setHsList(hsList);
        page.putField("detail",hr);

    }

    @Override
    public Site getSite() {
        return site;
    }


    /**
     * 将P标签替换成字符回车
     * @param str
     * @return
     */
    private String htmlToString(String str){
        str = str.replaceAll("</p>","\n");
        str = str.replaceAll("<p.*>+","");
        return str;
    }
    /**
     * 获取百分比
     * @param str
     * @return
     */
    private String strDel(String str){
        return str.substring(str.indexOf(":")+1,str.length() -1);
    }

}
