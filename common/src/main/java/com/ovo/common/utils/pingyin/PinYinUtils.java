package com.ovo.common.utils.pingyin;

import com.ovo.common.utils.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/***
 * 获取汉字转拼音
 */
public class PinYinUtils {
    public static StringBuffer sb = new StringBuffer();

    /**
     * 获取汉字字符串的首字母，英文字符不变
     * 例如：阿飞→af
     */
    public static String getPinYinHeadChar(String chines) {
        sb.setLength(0);
        char[] chars = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 128) {
                try {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(chars[i], defaultFormat)[0].charAt(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 获取汉字字符串的第一个字母
     */
    public static String getPinYinFirstLetter(String str) {
        sb.setLength(0);
        char c = str.charAt(0);
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
        if (pinyinArray != null) {
            sb.append(pinyinArray[0].charAt(0));
        } else {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 获取汉字字符串的第一个字母
     */
    public static char getPinYinFirstChar(String str) {
        char c = str.charAt(0);
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
        if (pinyinArray != null) {
            c = pinyinArray[0].charAt(0);
        }
        return c;
    }

    /**
     * 获取汉字字符串的汉语拼音，英文字符不变
     */
    public static String getPinYin(String chines) {
        sb.setLength(0);
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(nameChar[i]);
            }
        }
        return sb.toString();
    }

    private static char[] strNum = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };
    /**
     * 获取汉字字符串的汉语拼音，英文字符不变
     */
    public static String getPinYin(char nameChar) {
        if (",".equals(String.valueOf(nameChar))
                || "，".equals(String.valueOf(nameChar))
                || ";".equals(String.valueOf(nameChar))
                || "；".equals(String.valueOf(nameChar))
                || ".".equals(String.valueOf(nameChar))
                || "。".equals(String.valueOf(nameChar))
                || "?".equals(String.valueOf(nameChar))
                || "？".equals(String.valueOf(nameChar))
                || "!".equals(String.valueOf(nameChar))
                || "！".equals(String.valueOf(nameChar))
                || "“".equals(String.valueOf(nameChar))
                || "\"".equals(String.valueOf(nameChar))
                || "”".equals(String.valueOf(nameChar))
                || ":".equals(String.valueOf(nameChar))
                || "：".equals(String.valueOf(nameChar)))return String.valueOf(nameChar);
        if ("~".equals(String.valueOf(nameChar))){
            nameChar = '至';
        }else if (StringUtils.isNumeric(String.valueOf(nameChar))){
            nameChar = strNum[Integer.valueOf(String.valueOf(nameChar))];
        }else if (" ".equals(String.valueOf(nameChar))){
            return "";
        }
//        LogUtils.log(nameChar);
        sb.setLength(0);
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //匹配是否是汉字
        if (Character.toString(nameChar).matches("[\\u4E00-\\u9FA5]+")) {
            try {
                sb.append(PinyinHelper.toHanyuPinyinStringArray(nameChar, defaultFormat)[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            sb.append(nameChar);
        }
//        if (nameChar > 128) {
//            try {
//                sb.append(PinyinHelper.toHanyuPinyinStringArray(nameChar, defaultFormat)[0]);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            sb.append(nameChar);
//        }
        return sb.toString();
    }
}
