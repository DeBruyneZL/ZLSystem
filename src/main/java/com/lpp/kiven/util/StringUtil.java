package com.lpp.kiven.util;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	/**
     * 
     * @param obj
     * @return 为空返回true
     * @Description:判断对象是否是空，字符串空，字符序列空，集合空，map空，对象数组空
     * @Author:Dable
     * @Since:2014年7月9日
     */
    public static boolean isNullOrEmpty(Object obj) {
	if (obj == null) {
        return true;
    }
	if (obj instanceof String) {
	    if ("".equals(obj) || "null".equals(obj) || "undefined".equals(obj)) {
		return true;
	    }
	}
	if (obj instanceof CharSequence) {
        return ((CharSequence) obj).length() == 0;
    }

	if (obj instanceof Collection) {
        return ((Collection) obj).isEmpty();
    }

	if (obj instanceof Map) {
        return ((Map) obj).isEmpty();
    }

	if (obj instanceof List) {
	    return ((List) obj).size() == 0;
	}
	if (obj instanceof Object[]) {
	    Object[] object = (Object[]) obj;
	    boolean empty = true;
	    for (int i = 0; i < object.length; i++) {
            if (!isNullOrEmpty(object[i])) {
                empty = false;
                break;
            }
        }
	    return empty;
	}
	return false;
    }
    
    public static String getUUID32(){
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    //  return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
    
    public static String changNull(String str) {
        if (str == null || "null".equals(str)) {
            return "";
        } else {
            return str;
        }
    }
    
    public static String yuanToFen(String yuan, int newScale)
    {
      String fen = "";
      BigDecimal y = new BigDecimal(yuan.trim());
      BigDecimal m = new BigDecimal("100");
      fen = y.multiply(m).setScale(newScale).toString();
      return fen;
    }

    public static String fenToYuan(String fen)
    {
      String yuan = "";
      BigDecimal f = new BigDecimal(fen.trim());

      BigDecimal m = new BigDecimal("100");

      yuan = f.divide(m, 2, 1).toString();
      return yuan;
    }

    /**
     * 高精度的乘法
     * @return
     */
    public static String multiply(String str1,String str2){
        String result = "";

        BigDecimal m = new BigDecimal(str1.trim());
        BigDecimal s = new BigDecimal(str2.trim());
        result=m.multiply(s).toString();

        return result;
    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param str1 被除数
     * @param str2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static String div(String str1,String str2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(str1.trim());
        BigDecimal b2 = new BigDecimal(str2.trim());
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 高精度的加法
     * @return
     */
    public static String add(String str1,String str2){
        String result = "";

        BigDecimal m = new BigDecimal(str1.trim());
        BigDecimal s = new BigDecimal(str2.trim());
        result=m.add(s).toString();

        return result;
    }

    /**
     * 高精度的减法
     * @return
     */
    public static String less(String str1,String str2){
        String result = "";

        BigDecimal m = new BigDecimal(str1.trim());
        BigDecimal s = new BigDecimal(str2.trim());
        result=m.subtract(s).toString();

        return result;
    }

    public static void main(String[] args) {
        System.out.println(div("20","3" ,3));
    }
    /**
	 * 判断N个对象中是否有空，只要有一个为空则返回true
	 * @param objects
	 * @return
	 * @author ServerZhang
	 * @date 2017年11月30日
	 */
	public static boolean isEmpty(Object... objects){
		if (objects == null) {
			return false;
		}
		for(Object object : objects){
			if (null == object || "".equals(object.toString().trim())
                    || "null".equals(object.toString().toLowerCase().trim())) {
				return true;
			}
		}
		return false;
	}
    public static boolean isEmpty(Object object){
    	return null == object || "".equals(object.toString().trim())
                || "null".equals(object.toString().toLowerCase().trim());
    }
    /**
     * @param original 原价
     * @param current  现价
     * @return
     * 算折扣率
     */
    public static String division(String original,String current ){
        if ("0".equals(original)||"0.0".equals(original)||"0.00".equals(original)) {
            return "0.00";
        } else {
            return div(current,original,2);
        }
    }
    /**
     *
     * 把字符串转换成整数
     * @param s
     */
    public static int stringtoInt(String s) {
        BigDecimal amcount = new BigDecimal(s.trim());
        String samcount = amcount.toString();
        if (samcount.indexOf(".") != -1) {
            samcount = samcount.substring(0, samcount.indexOf("."));
        }
        return Integer.parseInt(samcount);
    }

    /**
     * 获取随机字符串
     * @param length 生成字符串的长度
     * @return
     * add by zhangliang 2020/04/03
     */
    public static String getRandomString(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 判断汉字  是汉字--true
     * @param c
     * @return
     * add by zhangliang 2020/04/03
     */
    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

    /**
     * @param phone 字符串类型的手机号
     * 传入手机号,判断后返回
     * true为手机号,false相反
     * */
    public static boolean isPhone(String phone) {
        String regex = "^[1][3,4,5,7,8,9][0-9]{9}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }

    /**
     * 判断字符串内容是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        for (int i = 0; i < str.length(); i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
    /**
     * 判断List<String>内容是否存在重复
     * @param stringList
     * @return
     */
    public static boolean checkIsRepeat(List<String> stringList) {
        HashSet<String> hashSet = new HashSet<String>();
        for (int i = 0; i < stringList.size(); i++) {
            hashSet.add(stringList.get(i));
        }
        if (hashSet.size() == stringList.size()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断数组内数据是否重复 不重复则返回true
     * @param array
     * @return
     */
    public static boolean checkArrIsRepeat(String[] array) {
        HashSet<String> hashSet = new HashSet<String>();
        for (int i = 0; i < array.length; i++) {
            hashSet.add(array[i]);
        }
        if (hashSet.size() == array.length) {
            return true;
        } else {
            return false;
        }
    }
}
