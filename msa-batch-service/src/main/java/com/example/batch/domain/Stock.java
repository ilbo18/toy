package com.example.batch.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "current_price")
    private Long currentPrice;

    @Column(name = "full_time")
    private Long fullTime;

    @Column(name = "fluctuation_rate")
    private String fluctuationRate;

    @Column(name = "face_value")
    private Long faceValue;

    @Column(name = "market_cap")
    private Long marketCap;

    @Column(name = "shares")
    private Long shares;

    @Column(name = "per_foreigners")
    private String perForeigners;

    @Column(name = "trading_volume")
    private Long tradingVolume;

    @Column(name = "per")
    private String per;

    @Column(name = "roe")
    private String roe;

    @Column(name = "url")
    private String url;

    @Builder
    public Stock(String name, Long currentPrice, Long fullTime, String fluctuationRate, Long faceValue, Long marketCap, Long shares, String perForeigners, Long tradingVolume, String per, String roe, String url) {
        this.name = name;
        this.currentPrice = currentPrice;
        this.fullTime = fullTime;
        this.fluctuationRate = fluctuationRate;
        this.faceValue = faceValue;
        this.marketCap = marketCap;
        this.shares = shares;
        this.perForeigners = perForeigners;
        this.tradingVolume = tradingVolume;
        this.per = per;
        this.roe = roe;
        this.url = url;
    }
}
