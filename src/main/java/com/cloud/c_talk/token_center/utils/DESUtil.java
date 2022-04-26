package com.cloud.c_talk.token_center.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESUtil {

    private static final String key = "dwqdASgKLjdwqojirRR#4BHY";

    private final static Logger logger = LoggerFactory.getLogger(DESUtil.class);

    public static String encrypt(String srcStr) {
        try {
            byte[] keyByte = hex();
            byte[] src = srcStr.getBytes();
            //生成密钥
            SecretKey desKey = new SecretKeySpec(keyByte, "DESede");
            //加密
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(Cipher.ENCRYPT_MODE, desKey);
            return org.apache.commons.codec.binary.Base64.encodeBase64String(c1.doFinal(src));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    private static byte[] hex(){
        String f = DigestUtils.md5Hex(key);
        byte[] bkeys = new String(f).getBytes();
        byte[] enk = new byte[24];
        for (int i = 0; i < 24; i ++){
            enk[i] = bkeys[i];
        }
        return enk;
    }

    public static String decrypt(String desStr) {
        try {
            org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
            byte[] keyByte = hex();
            byte[] src = base64.decode(desStr);
            //生成密钥
            SecretKey desKey = new SecretKeySpec(keyByte, "DESede");
            //解密
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(Cipher.DECRYPT_MODE, desKey);
            return new String(c1.doFinal(src));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(decrypt(encrypt("哈哈哈哈哈哈")));
    }

}
