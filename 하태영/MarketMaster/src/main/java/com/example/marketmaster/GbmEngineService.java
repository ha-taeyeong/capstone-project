package com.example.marketmaster;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GbmEngineService {
    private double currentPrice = 50000.0; // 시작가 5만원
    private double mu = 0.0002;    // 기대수익률
    private double sigma = 0.015;  // 변동성
    private final Random random = new Random();
    private long lastTimestamp = System.currentTimeMillis() / 1000;

    public void injectPattern(String type) {
        switch (type) {
            case "golden" -> { this.mu = 0.005; this.sigma = 0.012; } // 상승
            case "dead" -> { this.mu = -0.005; this.sigma = 0.015; }  // 하강
            case "reset" -> { this.mu = 0.0002; this.sigma = 0.015; } // 초기화
        }
    }

    private void generateNextPrice() {
        double dt = 0.05;
        double drift = mu * currentPrice * dt;
        double diffusion = sigma * currentPrice * Math.sqrt(dt) * random.nextGaussian();
        currentPrice += (drift + diffusion);
        if (currentPrice < 1000) currentPrice = 1000;
    }

    public CandleDto generateCandle() {
        double open = currentPrice;
        double high = open, low = open;
        for(int i = 0; i < 5; i++) {
            generateNextPrice();
            high = Math.max(high, currentPrice);
            low = Math.min(low, currentPrice);
        }
        lastTimestamp += 1;
        return new CandleDto(open, high, low, currentPrice, lastTimestamp);
    }
}