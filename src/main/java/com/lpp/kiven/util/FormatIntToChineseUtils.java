package com.lpp.kiven.util;
public class FormatIntToChineseUtils {
    public static String formatInteger(int num) {
        String[] units = { "", "十", "百", "千", "万", "十万", "百万", "千万", "亿",
                "十亿", "百亿", "千亿", "万亿" };
        char[] numArray = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };
        char[] val = String.valueOf(num).toCharArray();
        int len = val.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String m = val[i] + "";
            int n = Integer.valueOf(m);
            boolean isZero = n == 0;
            String unit = units[(len - 1) - i];
            if (isZero) {
                if ('0' == val[i - 1]) {
                    continue;
                } else {
                    sb.append(numArray[n]);
                }
            } else {
                sb.append(numArray[n]);
                sb.append(unit);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int num = 2;
        String numStr = formatInteger(num);
        System.out.println("num= " + num + ", convert result: " + numStr);
    }
}
