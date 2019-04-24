package com.gameloft9.demo.webmagic.template;

import com.gameloft9.demo.dataaccess.model.user.HeroDetail;
import com.gameloft9.demo.dataaccess.model.user.HeroSkill;
import com.gameloft9.demo.dataaccess.model.user.ReptileUserContentStatistics;
import com.gameloft9.demo.dataaccess.model.user.ReptileUserRecentMatch;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 提取模板
 */
public class ExtractTemplate {


    /**
     * 获取百分比
     * @param str
     * @return
     */
    private static String strDel(String str){
        return str.substring(str.indexOf(":")+1,str.length() -1);
    }

    /**
     * 将P标签替换成字符回车
     * @param str
     * @return
     */
    private static String htmlToString(String str){
        str = str.replaceAll("</p>","");
        str = str.replaceAll("<p.*>+","");
        return str;
    }

    /**
     * 爬取用户基本数据
     */
    public static void extractUserBaseTemplate(Selectable selectable){

    }

    /**
     * 提取各赛季统计数据模板
     * @param serverId
     * @param reptileId
     * @param selectable
     * @return
     */
    public static List<ReptileUserContentStatistics> extractContentStatisticsTemplat(String serverId, String reptileId ,Selectable selectable){

        List<ReptileUserContentStatistics> resultList = new ArrayList<>();
        //获取html标签中的tr集合
        List<Selectable> trList = selectable.xpath("//table[@class=\"table table-hover recent-game-list\"]/tbody/tr").nodes();
        if (trList != null && trList.size() > 0) {
            for (int i = 0, num = trList.size(); i < num; i++) {
                Selectable tr = trList.get(i);
                List<Selectable> tdList = tr.xpath("//tr/td").nodes();
                if (tdList != null && tdList.size() == 6) {
                    ReptileUserContentStatistics result = new ReptileUserContentStatistics();
                    result.setServerId(serverId);
                    result.setReptileId(reptileId);
                    //赛季类型
                    result.setContentType(tdList.get(0).xpath("//td/text()").get());
                    //胜场
                    int a = Integer.valueOf(tdList.get(3).xpath("//td/text()").get().trim());
                    result.setWin(a);
                    //负场
                    a = Integer.valueOf(tdList.get(4).xpath("//td/text()").get().trim());
                    result.setLose(Integer.valueOf(a));
                    resultList.add(result);
                } else {
                    System.out.println("第 " + (i + 1) + " 行数据异常或无效");
                }
            }
        }else{
            System.out.println("赛季统计表，列异常");
        }
        return resultList;
    }

    /**
     * 获取英雄明细 - 数据处理模板
     * @param html
     * @return
     */
    public static HeroDetail extractHeroDetailTemplat(Html html){

        String equipId = "";
        //英雄出装的路径id
        if (html.xpath("//ul[@class=\"mod-tab-trigger J_nav\"]/li/@record").get() != null) {
            equipId = html.xpath("//ul[@class=\"mod-tab-trigger J_nav\"]/li/@record").get().trim();
        }
        //System.out.println("装备抓取id：" + heroEquipId);
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

        String runeImg = "";
        String runeDes = "";
        //符文信息 Rune
        if (html.xpath("//div[@class=\"mod-tab-content\"]") != null
                && html.xpath("//div[@class=\"mod-tab-content\"]").nodes() != null
                && html.xpath("//div[@class=\"mod-tab-content\"]").nodes().size() > 1) {
            Selectable selectable1 = html.xpath("//div[@class=\"mod-tab-content\"]");
            //符文整体图片
            runeImg = selectable1.xpath("//div[@class=\"mod-tab-content\"]/img/@src").get();
            //符文选择描述
            runeDes = selectable1.xpath("//div[@class=\"desc\"]/html()").get();
        }
        System.out.println("runeImg:" + runeImg);
        System.out.println("runeDes:" + htmlToString(runeDes));

        //英雄详情实体
        HeroDetail hd = new HeroDetail();
        hd.setEquipId(equipId);
        hd.setRuneImg(runeImg);
        hd.setRuneDes(htmlToString(runeDes));

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
        return hd;
    }

    /**
     * 获取英雄技能 - 数据处理模板
     * @param html
     * @return
     */
    public static List<HeroSkill> extractHeroSkillTemplat(Html html){
        //英雄名
        String heroName = html.xpath("//h1[@class=\"hero-name\"]/text()").get().trim();
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
        return hsList;
    }

    /**
     * 装备出装模板
     * @param html
     */
    public static void extractEquipTemplat(Html html){
        //英雄名
        String heroName = html.xpath("//h1[@class=\"hero-name\"]/text()").get().trim();

        //推荐加点 - 出装
        List<Selectable> selectableList = html.xpath("//div[@class=\"hero-guide__row\"]").nodes();
        //装备推荐出装块
        Selectable equipSelectable = selectableList.get(1);
        List<Selectable> esList = equipSelectable.xpath("//div[@class=\"hero-box ext-equip\"]").nodes();
        for (int i = 0 , num = esList.size(); i < num ; i ++ ){

            //出装时间
            System.out.println(
                    esList.get(i).xpath("/html()")
            );
            //出装时间
            System.out.println(
                    esList.get(i).xpath("//div[@class=\"hero-box ext-equip\"]/div[@class=\"hero-box__hd\"]")
            );
            //出装装备
            System.out.println(
                    esList.get(i).xpath("//div[@class=\"hero-box ext-equip\"]/html()")
            );
            Selectable select = esList.get(i).xpath("/div[@class=\"hero-box ext-equip\"]/div[@class=\"hero-box__bd\"]/div[@class=\"hero-equip\"]");
            extractEquipDetailTemplat(html,select,heroName);
        }
    }

    /**
     * 英雄出装详情 - 装备名称，物品等
     * @param select
     */
    private static void extractEquipDetailTemplat(Html html,Selectable select,String heroName){
        Selectable s = select.xpath("/div[@class=\"hero-equip\"]/div[@class=\"hero-equip__list\"]");
        //hero-equip__list
        String s1 = select.xpath("//div[@class=\"hero-equip\"]/div[@class=\"hero-equip__list\"]/html()").get();
        List<Selectable> imgList = select.xpath("//div[@class=\"hero-equip\"]/div[@class=\"hero-equip__list\"]/img").nodes();
        for (int i = 0, num = imgList.size(); i < num ; i ++){
            String alt = imgList.get(i).xpath("//img/@alt").get();
            System.out.println(alt);
            System.out.println(imgList.get(i).xpath("//img/@src"));
            //装备详情代码块 equip_3071
            Selectable tips = html.xpath("//div[@class=\"hero-tips equip_" + alt + "\"]");
            String eName = tips.xpath("//h4").get();
            String price = tips.xpath("//p/text()").get();
            System.out.println("装备名：" + eName);
            System.out.println("价格：" + price);
        }
        System.out.println("描述" + select.xpath("//div[@class=\"hero-equip\"]/p[@class=\"hero-equip__txt\"]/text()"));
    }

    /**
     * 提取最近比赛数据模板
     * @param serverId
     * @param reptileId
     * @param selectable
     * @return
     */
    public static List<ReptileUserRecentMatch> extractRecentMatchTemplat(String serverId, String reptileId , Selectable selectable){
    //.xpath("//table[@class=\"table table-hover recent-game-list\"]/tbody/allText()")
        List<ReptileUserRecentMatch> rmList = new ArrayList<>();

        List<Selectable> trList = selectable.xpath("//table[@class=\"table table-hover recent-game-list\"]/tbody/tr").nodes();
        if (trList != null && trList.size() > 0) {
            for (int i = 0, num = trList.size(); i < num; i++) {
                Selectable tr = trList.get(i);
                List<Selectable> tdList = tr.xpath("//tr/td").nodes();
                if (tdList != null && tdList.size() == 5) {
                    ReptileUserRecentMatch rm = new ReptileUserRecentMatch();
                    rm.setReptileId(reptileId);
                    rm.setServerId(serverId);
                    rm.setHeroName(tdList.get(0).xpath("//td/a/img/@title").get().trim());
                    rm.setGameUrl(tdList.get(0).xpath("//td/a/@href").get().trim());
                    rm.setHeroImg(tdList.get(0).xpath("//td/a/img/@src").get().trim());
                    rm.setGameType(tdList.get(1).xpath("//td/text()").get().trim());
                    rm.setResult(tdList.get(2).xpath("//td/span/text()").get().trim());
                    rm.setGameTime(tdList.get(3).xpath("//td/text()").get());
                    rmList.add(rm);
                } else {
                    System.out.println("第 " + (i + 1) + " 行数据异常或无效");
                }
            }
        }else{
            System.out.println("最近比赛表表，列异常");
        }
        return rmList;
    }


    /**
     * 提取英雄使用明细模板
     * @param selectable
     */
    public static void extractUserHeroDetailTemplat(Selectable selectable){
        //.xpath("//table[@class=\"table table-hover recent-game-list\"]/tbody/allText()")
        List<Selectable> trList = selectable.xpath("//table[@class=\"table table-hover recent-game-list\"]/tbody/tr").nodes();
        if (trList != null && trList.size() > 0) {
            for (int i = 0, num = trList.size(); i < num; i++) {
                Selectable tr = trList.get(i);
                List<Selectable> tdList = tr.xpath("//tr/td").nodes();
                if (tdList != null && tdList.size() == 10) {
                    System.out.println("英雄：" + tdList.get(0).xpath("//td/img/@title").get());
                    System.out.print("场次：" + tdList.get(1).xpath("//td/text()").get());
                    System.out.print("胜率：" + tdList.get(2).xpath("//td/text()").get());
                    System.out.print("战损：" + tdList.get(3).xpath("//td/text()").get());
                    System.out.print("杀死助：" + tdList.get(4).xpath("//td/text()").get());
                    System.out.print("对英雄输出：" + tdList.get(5).xpath("//td/text()").get());
                    System.out.print("分钟经济：" + tdList.get(6).xpath("//td/text()").get());
                    System.out.print("10分钟补兵：" + tdList.get(7).xpath("//td/text()").get());
                    System.out.print("MVP&Carry：" + tdList.get(8).xpath("//td/text()").get());
                    System.out.println("上次使用：" + tdList.get(9).xpath("//td/text()").get());
                } else {
                    System.out.println("第 " + (i + 1) + " 行数据异常或无效");
                }
            }
        }else{
            System.out.println("英雄明细表，列异常");
        }
    }


}
