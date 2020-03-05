package com.mengfei.fbsepjava.utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final String SYMBOLS = "0123456789";
    private static final Random RANDOM = new SecureRandom();


    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replaceAll("-","");
        return str;
    }

    public static boolean testPhone(String str) {
        Pattern pat = Pattern.compile("^[1][3578][0-9]{9}$");
        Matcher mat = pat.matcher(str);
        return mat.find();
    }

    /**
     * 生成6位随机数字
     * @return 返回6位数字验证码
     */
    public static String generateVerCode() {
        char[] nonceChars = new char[6];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }


}
