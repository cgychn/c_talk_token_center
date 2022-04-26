package com.cloud.c_talk.token_center.deal;

import com.alibaba.fastjson.JSONObject;
import com.cloud.c_talk.token_center.center.TokenCenter;
import com.cloud.c_talk.token_center.entity.Token;
import com.cloud.c_talk.token_center.utils.DESUtil;
import com.cloud.c_talk.token_center.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenDealer {

    private static final Logger logger = LoggerFactory.getLogger(TokenDealer.class);

    /**
     * 解密token
     * @param token
     * @return
     */
    private static Token decryptToken (String token) {
        try {
            return JSONObject.parseObject(DESUtil.decrypt(token), Token.class);
        } catch (Exception e) {
            logger.error("token decrypt failed ", e);
            return null;
        }
    }

    /**
     * 检查token是否合法
     * @param tokenString
     * @return
     */
    public static Token checkTokenInvalidAndToken (String tokenString) {
        Token token = decryptToken(tokenString);
        System.out.println(token);
        if (token == null || checkForge(token) || checkOverTime(token) ) {
            return null;
        }
        return token;
    }

    /**
     * 检查是否超时
     * @param token
     * @return
     */
    public static boolean checkOverTime (Token token) {
        long current = System.currentTimeMillis();
        // 计算超时的时间戳
        long time = token.getLastLoggedTime() + token.getExpiryTime();
        // 如果超时时间戳 > 当前时间，没超时，更新时间戳
        if (time > current) {
            return false;
        }
        // 从token中心删除
        TokenCenter.removeToken(token);
        return true;
    }

    /**
     * 检查是否伪造
     * @param token
     * @return
     */
    private static boolean checkForge (Token token) {
        if (StringUtils.isEmpty(token.getHash())) {
            return true;
        }
        return !token.getHash().equals(MD5Util.encrypt(token.getUsername() + " " + token.getUuid())) ||
                !TokenCenter.checkTokenRegistered(token);
    }

    /**
     * 生成token
     * @param username
     * @return
     */
    public static String generateTokenString (String username) {
        Token token = new Token(username);
        return generateTokenString(token);
    }

    /**
     * 更新token
     * @param token
     * @return
     */
    public static String generateTokenString (Token token) {
        // 注册到token中心
        TokenCenter.registerToken(token);
        return DESUtil.encrypt(token.toString());
    }

}
