package com.ovo.common.utils;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtils {
	
	private static Pattern numericPattern = Pattern.compile("^[0-9\\-]+$");
    private static Pattern numericStringPattern = Pattern
            .compile("^[0-9\\-\\-]+$");
    private static Pattern abcPattern = Pattern.compile("^[a-z|A-Z]+$");
    public static final String splitStrPattern = ",|，|;|；|、|\\.|。|-|_|\\(|\\)|\\[|\\]|\\{|\\}|\\\\|/| |　|\"";

    
	/**
	 * 创建目录
	 * @param sDir
	 * @return
	 */
	public static synchronized File createFileDir(String sDir){
		File file = new File(sDir);
		if (!file.exists()){
			file.mkdirs();
		}
		return file;
	}

    /***
     * 消除\n
     * @param result
     * @return
     */
	public static String removeStr(String result){
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(result);
        result = m.replaceAll("");
        return result;
    }
	
	//为字母
	public static boolean isCharacter(String str) {
		Pattern pattern = Pattern.compile("^[A-Za-z]+$"); 
		return pattern.matcher(str).matches(); 
	}
	
	//为数字或者字母
	public static boolean isNumAndChar(String str) {
		Pattern pattern = Pattern.compile("^[\\da-zA-Z]*$"); 
		return pattern.matcher(str).matches(); 
	}
	
	//将字符数组转成字符串
	public static String joinStringArray(String[] str, String separate) {
		StringBuffer buf = new StringBuffer();
		for(int i=0; i < str.length; i++){
			buf.append(str[i]);
			if (i != (str.length - 1)) {
				buf.append(separate);
			}
		}
		return buf.toString();
	}
	
	//将字符数组转成字符串
	public static String joinStringArray(List<String> lstStr, String separate) {
		StringBuffer buf = new StringBuffer();
		for(int i=0; i < lstStr.size(); i++){
			buf.append(lstStr.get(i));
			if (i != (lstStr.size() - 1)) {
				buf.append(separate);
			}
		}
		return buf.toString();
	}
	
	//字符串转成数组
	public static void stringSplit2List(String str, List<String> lst) {
		if (!str.equals("")) {
			String[] array = str.split(",");
			for (int i = 0; i < array.length; ++ i) {
				lst.add(array[i]);
			}
		}
	}
	
	/**
     * 判断是否是空字符串 null和"" 都返回 true
     */
    public static boolean isEmpty(String s) {
        if (s != null && !"".equals(s)) {
            return false;
        }
        return true;
    }
    
    /**
     * 判断是否数字表示
     * 
     * @param src
     *            源字符串
     * @return 是否数字的标志
     */
    public static boolean isNumeric(String src) {
        boolean return_value = false;
        if (src != null && src.length() > 0) {
            Matcher m = numericPattern.matcher(src);
            if (m.find()) {
                return_value = true;
            }
        }
        return return_value;
    }

    /**
     * 判断是否数字表示,且长度在5到12位之间
     *
     * @param src
     *            源字符串
     * @return 是否数字的标志
     */
    public static boolean isNumAndLen(String src) {
        boolean return_value = false;
        if (src != null && src.length() > 0) {
            Matcher m = numericPattern.matcher(src);
            if (m.find() && src.length() >= 5 && src.length() <= 12) {
                return_value = true;
            }
        }
        return return_value;
    }

    /**
     * 判断是否数字表示
     * 
     * @param src
     *            源字符串
     * @return 是否数字的标志
     */
    public static boolean isNumericString(String src) {
        boolean return_value = false;
        if (src != null && src.length() > 0) {
            Matcher m = numericStringPattern.matcher(src);
            if (m.find()) {
                return_value = true;
            }
        }
        return return_value;
    }
    
    /**
     * 判断是否纯字母组合
     * 
     * @param src
     *            源字符串
     * @return 是否纯字母组合的标志
     */
    public static boolean isABC(String src) {
        boolean return_value = false;
        if (src != null && src.length() > 0) {
            Matcher m = abcPattern.matcher(src);
            if (m.find()) {
                return_value = true;
            }
        }
        return return_value;
    }
    
    /**
     * 
     * 截取字符，不转码
     * 
     * @param subject
     * @param size
     * @return
     */
    public static String subStrNotEncode(String subject, int size) {
        if (subject.length() > size) {
            subject = subject.substring(0, size);
        }
        return subject;
    }

    /**
     * 判断是否是手机号码
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isPhone(String str)
            throws PatternSyntaxException {
        if (isEmpty(str))return false;
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
