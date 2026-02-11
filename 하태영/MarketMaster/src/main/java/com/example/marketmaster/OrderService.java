package com.example.marketmaster;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private double userCash = 1000000.0; // 초기 자산: 100만 원
    private int stockQuantity = 0;

    public synchronized String processOrder(OrderRequest request, double currentPrice) {
        if ("BUY".equals(request.getType())) {
            double totalCost = currentPrice * request.getQuantity();
            if (userCash >= totalCost) {
                userCash -= totalCost;
                stockQuantity += request.getQuantity();
                return "매수 체결 완료";
            }
        } else if ("SELL".equals(request.getType())) {
            if (stockQuantity >= request.getQuantity()) {
                userCash += currentPrice * request.getQuantity();
                stockQuantity -= request.getQuantity();
                return "매도 체결 완료";
            }
        }
        return "잔액 또는 수량 부족";
    }
}