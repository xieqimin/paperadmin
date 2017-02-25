package com.wit.paperadmin.Utils;

import java.security.MessageDigest;

/**
 * Created by linuxyf on 2016/10/17.
 */
public class Md5Wrapper {
    /**
     * 返回给定字符串的MD5散列值
     * @param val， 给定的字符串
     * @return 散列结果
     */
    public static String getMD5(String val){
        char hexDigits[]={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = val.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}
