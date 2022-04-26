package com.cloud.c_talk.token_center.center;

import com.cloud.c_talk.token_center.entity.Token;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class TokenCenter {

    private static final HashMap<String, Token> tokens = new HashMap<>();

    public synchronized static void registerToken (Token token) {
        tokens.put(token.getUuid(), token);
    }

    public static boolean checkTokenRegistered (Token token) {
        return tokens.containsKey(token.getUuid());
    }

    public static void removeToken (Token token) {
        tokens.remove(token.getUuid());
    }

    public synchronized static void removeTokenByUsername (String username) {
        if (StringUtils.isEmpty(username)) {
            return;
        }
        for (Map.Entry<String, Token> tokenEntry : tokens.entrySet()) {
            if (username.equals(tokenEntry.getValue().getUsername())) {
                tokens.remove(tokenEntry.getKey());
            }
        }
    }

    public static HashMap<String, Token> getTokens () {
        return tokens;
    }

}
