package com.lee.supersuse.utils;

/**
 * 用于快捷修改html字符串
 */
public class HtmlUtil {

    public static String addFile(String html,String affId){
        StringBuilder stringBuilder = new StringBuilder(html);
        stringBuilder.append("<hr/>" +
                "<a href='checkFile/'"+affId+" >查看附件</a>");
        return stringBuilder.toString();
    }




}
