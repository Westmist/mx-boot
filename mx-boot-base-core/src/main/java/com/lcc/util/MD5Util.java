package com.lcc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Congcong Liao
 * @since 2021-01-26
 **/
public class MD5Util {
    /**
     * MD5加密类
     * @param src 要加密的字符串
     * @return    加密后的字符串
     */
    public static String encryptMD5(String src){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}