package com.ovo.common.utils.pingyin;

/**
 * 拆分声母韵母
 */
public class PinyinUtil {
    private static final String[] Shengmu = { "b", "p", "m", "f", "d", "t", "l", "n",
            "g", "k", "h", "j", "q", "x", "z",
            "c", "s", "zh", "ch", "sh", "y", "w", "r" };

    private static final String[] Yunmu = { "a", "o", "e", "i", "u", "v", "ai", "ei", "ui",
            "ao", "ou", "iu", "ie",
            "ue", "ve", "er", "an", "en", "in", "un", "ang", "eng", "ing", "ong"
            /*, "iong", "iang", "uang", "uan", "ian"*/ };

    public static String polishPinyin(String name) {
        if (name == null || "".equals(name))
            return name;

        if (",".equals(name)
                || "，".equals(name)
                || ";".equals(name)
                || "；".equals(name)
                || ".".equals(name)
                || "。".equals(name)
                || "?".equals(name)
                || "？".equals(name)
                || "!".equals(name)
                || "！".equals(name)
                || "“".equals(name)
                || "”".equals(name)
                || ":".equals(name)
                || "\"".equals(name)
                || "：".equals(name))return "|,";

        name = name.replaceAll("[0-9_]+", "");

        StringBuffer buffer = new StringBuffer();
        char[] chars = name.toCharArray();
        int size = chars.length;

        int i = 0;
        boolean flag = true;// 当前检测的是声母还是韵母
        while (i < size) {
            if (flag) {
                // 先判断前两位是不是声母
                if (i + 1 < size && PinyinUtil.isShengmu(chars[i], chars[i + 1])) {
                    buffer.append("|");
                    buffer.append(chars, i, 2);
                    buffer.append("&");
                    i += 2;
                    flag = false;
                } else if (PinyinUtil.isShengmu(chars[i])) {
                    buffer.append("|");
                    buffer.append(chars[i]);
                    buffer.append("&");
                    flag = false;
                    i++;
                } else {
                    flag = false;
                    buffer.append("&");
//                    i++;
                }
                if (i == size)
                    buffer.append("%");
            } else {
                /*if (i + 3 < size && PinyinUtil.isYunmu(chars[i], chars[i + 1],
                    chars[i + 2], chars[i + 3])) {
                    buffer.append(chars, i, 4);
                    i += 4;
                }else */if (i + 2 < size && PinyinUtil.isYunmu(chars[i], chars[i + 1], chars[i + 2])) {
                    buffer.append(chars, i, 3);
                    i += 3;
                } else if (i + 1 < size && PinyinUtil.isYunmu(chars[i], chars[i + 1])) {
                    buffer.append(chars, i, 2);
                    i += 2;
                } else if (PinyinUtil.isYunmu(chars[i])) {
                    buffer.append(chars[i]);
                    i++;
                } else {
                    buffer.append("%");
                }
                flag = true;
            }
        }

        return buffer.toString();
    }

    public static boolean isShengmu(String str) {
        for (String shengmu : Shengmu)
            if (shengmu.equalsIgnoreCase(str))
                return true;
        return false;
    }

    public static boolean isShengmu(char c) {
        return isShengmu(String.valueOf(c));
    }

    public static boolean isShengmu(char c1, char c2) {
        char[] chars = { c1, c2 };
        return isShengmu(String.valueOf(chars));
    }

    public static boolean isYunmu(String str) {
        for (String yunmu : Yunmu)
            if (yunmu.equalsIgnoreCase(str))
                return true;
        return false;
    }

    public static boolean isYunmu(char c) {
        return isYunmu(String.valueOf(c));
    }

    public static boolean isYunmu(char c1, char c2) {
        char[] chars = { c1, c2 };
        return isYunmu(String.valueOf(chars));
    }

    public static boolean isYunmu(char c1, char c2, char c3) {
        char[] chars = { c1, c2, c3 };
        return isYunmu(String.valueOf(chars));
    }

    public static boolean isYunmu(char c1, char c2, char c3, char c4) {
        char[] chars = { c1, c2, c3, c4 };
        return isYunmu(String.valueOf(chars));
    }
}
