package com.example.marketmaster;

import lombok.Data;

@Data
public class OrderRequest {
    private String type; // BUY 또는 SELL
    private double price; // 주문 당시 가격
    private int quantity; // 수량
}