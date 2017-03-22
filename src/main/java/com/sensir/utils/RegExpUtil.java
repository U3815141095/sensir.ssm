package com.sensir.utils;

import java.util.regex.Pattern;

/**
 * 正则工具类
 */
public class RegExpUtil {

    /**
     * 判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
