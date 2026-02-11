package com.example.marketmaster;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// MarketDataScheduler.java
@Component
@RequiredArgsConstructor
public class MarketDataScheduler {
    private final SimpMessagingTemplate messagingTemplate;
    private final GbmEngineService engineService;

    @Scheduled(fixedRate = 500) // 0.5초마다 봉 데이터 생성 및 전송 [cite: 23, 24]
    public void broadcastCandle() {
        CandleDto candle = engineService.generateCandle();
        // /topic/ticker 채널로 OHLC 객체 전송
        messagingTemplate.convertAndSend("/topic/ticker", candle);
    }
}