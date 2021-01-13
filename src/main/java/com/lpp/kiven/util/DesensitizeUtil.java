package com.lpp.kiven.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ：zhangliang
 * @date ：Created in 2020/6/24 10:22
 * @description： 信息脱敏工具
 * @modified By：
 * @version: $
 */
public class DesensitizeUtil {
    /**
     * 保留前面几位 比如 姓名 张**
     * @param str
     * @param index
     * @return
     */
    public static String left(String str,int index) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        String name = StringUtils.left(str, index);
        return StringUtils.rightPad(name, StringUtils.length(str), "*");
    }

    /**
     *  前面保留 index 位明文，后面保留 end 位明文,如：[身份证号] 110****58，前面保留3位明文，后面保留2位明文
     * @param str
     * @param index
     * @param end
     * @return
     */
    public static String around(String str, int index, int end) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        return StringUtils.left(str, index).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(str, end), StringUtils.length(str), "*"), "***"));
    }

    /**
     * 保留后面几位 如手机号 *******5678
     * @param str
     * @param end
     * @return
     */
    public static String right(String str,int end) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        return StringUtils.leftPad(StringUtils.right(str, end), StringUtils.length(str), "*");
    }

    public static void main(String[] args) {
        System.out.println(DesensitizeUtil.around("510702199505043016",3,3));
    }
}
