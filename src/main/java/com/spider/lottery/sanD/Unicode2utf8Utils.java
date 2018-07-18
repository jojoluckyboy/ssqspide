package com.spider.lottery.sanD;
/**
 * Description：
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2018-05-09
 * Time：13:59
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
public class Unicode2utf8Utils {
    public static String convert(String unicodeString) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = -1;
        int pos = 0;
        while ((i = unicodeString.indexOf("\\u", pos)) != -1) {
            stringBuilder.append(unicodeString.substring(pos, i));
            if (i + 5 < unicodeString.length()) {
                pos = i + 6;
                stringBuilder.append((char) Integer.parseInt(unicodeString.substring(i + 2, i + 6), 16));
            }
        }
        return stringBuilder.toString();
    }
}