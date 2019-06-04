package com.dzcTourism.util;

import java.util.regex.Pattern;

public class StrUtils {

	/**
	 * 取出正文中的中文，截取前10个
	 * 
	 * @param subhead
	 * @return
	 */
	public static String extractContent(String subhead) {
	//	String reg = "[^\\u4e00-\\u9fa50-9]";
	//	subhead = subhead.replaceAll(reg, "");
		String newStr = "";
		if(subhead!=null && subhead.trim().length()>0) {
			newStr = removeHtmlTag(subhead);
			newStr = newStr.length() > 10 ? newStr.substring(0, 50) + "..." : newStr;
		}
		return newStr;
	}

	/**
     * 去除所有HTML标签
     * @param inputString
     * @return
     */
    public static String removeHtmlTag(String inputString) {
        // 含html标签的字符串
        String htmlStr = inputString; 
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        java.util.regex.Pattern p_special;
        java.util.regex.Matcher m_special;
        try {
            //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            // 定义HTML标签的正则表达式
            String regEx_html = "<[^>]+>";
            // 定义一些特殊字符的正则表达式 如：     
            String regEx_special = "\\&[a-zA-Z]{1,10};";

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            // 过滤script标签
            htmlStr = m_script.replaceAll(""); 
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            // 过滤style标签
            htmlStr = m_style.replaceAll(""); 
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            // 过滤html标签
            htmlStr = m_html.replaceAll(""); 
            p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
            m_special = p_special.matcher(htmlStr);
            // 过滤特殊标签
            htmlStr = m_special.replaceAll(""); 
            textStr = htmlStr;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return textStr;
    }
}
