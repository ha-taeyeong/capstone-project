package com.example.marketmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 필수: 실시간 데이터 생성을 위해 필요
public class MarketMasterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketMasterApplication.class, args);
    }
}