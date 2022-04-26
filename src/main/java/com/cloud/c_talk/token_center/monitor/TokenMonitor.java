package com.cloud.c_talk.token_center.monitor;

import com.cloud.c_talk.token_center.center.TokenCenter;
import com.cloud.c_talk.token_center.deal.TokenDealer;
import com.cloud.c_talk.token_center.entity.Token;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class TokenMonitor implements ApplicationRunner {

    private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        startMonitor();
    }

    private void startMonitor () {
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            for (Map.Entry<String, Token> entry : TokenCenter.getTokens().entrySet()) {
                if (TokenDealer.checkOverTime(entry.getValue())) {
                    TokenCenter.removeToken(entry.getValue());
                }
            }
        }, 0, 10, TimeUnit.SECONDS);
    }
}
