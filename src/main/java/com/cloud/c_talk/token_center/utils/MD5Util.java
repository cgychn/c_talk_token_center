package com.cloud.c_talk.token_center.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5Util {

    private final static String salt = "WhoIsYourDad";

    private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

    /**
     * MD5加密
     * @param str
     * @return
     */
    public static String encrypt (String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            str = str + salt;
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] b = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, b).toString(16);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}
