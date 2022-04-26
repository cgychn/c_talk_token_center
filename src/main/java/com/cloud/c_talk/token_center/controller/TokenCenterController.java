package com.cloud.c_talk.token_center.controller;

import com.cloud.c_talk.token_center.center.TokenCenter;
import com.cloud.c_talk.token_center.deal.TokenDealer;
import com.cloud.c_talk.token_center.entity.RequestTokenEntity;
import com.cloud.c_talk.token_center.entity.Token;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("token")
public class TokenCenterController {

    @PostMapping("generate")
    public String generateToken (@RequestBody RequestTokenEntity tokenEntity) {
        String userName = tokenEntity.getUsername();
        return TokenDealer.generateTokenString(userName);
    }

    @PostMapping("update")
    public String updateToken (@RequestBody Token token) {
        return TokenDealer.generateTokenString(token);
    }

    @PostMapping("check")
    public Token checkTokenInvalidAndGetToken (@RequestBody RequestTokenEntity tokenEntity) {
        return TokenDealer.checkTokenInvalidAndToken(tokenEntity.getToken());
    }

    @PostMapping("remove")
    public Boolean removeToken (@RequestBody Token token) {
        TokenCenter.removeToken(token);
        return true;
    }

    @PostMapping("remove/by/user")
    public Boolean removeTokenByUser (@RequestParam("username") String username) {
        System.out.println("remove token " + username);
        TokenCenter.removeTokenByUsername(username);
        return true;
    }

}
