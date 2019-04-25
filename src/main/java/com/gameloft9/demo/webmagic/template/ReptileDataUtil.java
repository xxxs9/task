package com.gameloft9.demo.webmagic.template;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬取数据处理方法
 */
public class ReptileDataUtil {


    /**
     * unicode转汉字字符串
     * @param str
     * @return
     */
    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            //group 6728
            String group = matcher.group(2);
            //ch:'木' 26408
            ch = (char) Integer.parseInt(group, 16);
            //group1 \u6728
            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str;
    }

    /**
     * 获取字符串的unicode编码
     * 汉字“木”的Unicode 码点为Ox6728
     *
     * @param s 木
     * @return \ufeff\u6728  \ufeff控制字符 用来表示「字节次序标记（Byte Order Mark）」不占用宽度
     * 在java中一个char是采用unicode存储的 占用2个字节 比如 汉字木 就是 Ox6728 4bit+4bit+4bit+4bit=2字节
     */
    public static String stringToUnicode(String s) {
        try {
            StringBuffer out = new StringBuffer("");
            //直接获取字符串的unicode二进制
            byte[] bytes = s.getBytes("unicode");
            //然后将其byte转换成对应的16进制表示即可
            for (int i = 0; i < bytes.length - 1; i += 2) {
                out.append("\\u");
                String str = Integer.toHexString(bytes[i + 1] & 0xff);
                for (int j = str.length(); j < 2; j++) {
                    out.append("0");
                }
                String str1 = Integer.toHexString(bytes[i] & 0xff);
                out.append(str1);
                out.append(str);
            }
            return out.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * serverName 转换成 serverId
     * @param serverName
     * @return
     */
    public static String toServerId(String serverName){

        StringBuffer str = new StringBuffer();
        String ser = serverName.substring(0,2);
        String id = serverName.substring(2,serverName.length());
        switch (ser){
            case "电信":str.append("dx");break;
            case "网通":str.append("wt");break;
            case "教育":str.append("jy");break;
            case "全网":str.append("qw");break;
            default: break;
        }
        if (id.length() == 2){
            str.append("1");
            id = id.substring(1,2);
        }
        if (id.length() == 1){
            switch (id){
                case "一": str.append("1");break;
                case "二": str.append("2");break;
                case "三": str.append("3");break;
                case "四": str.append("4");break;
                case "五": str.append("5");break;
                case "六": str.append("6");break;
                case "七": str.append("7");break;
                case "八": str.append("8");break;
                case "九": str.append("9");break;
                default:break;
            }
        }
        return str.toString();
    }
}
