package com.example.stock.dto;

import com.example.stock.domain.Stock;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class StockDto {

    private Long id;

    private String name;

    private Long currentPrice;

    private Long fullTime;

    private String fluctuationRate;

    private Long faceValue;

    private Long marketCap;

    private Long shares;

    private String perForeigners;

    private Long tradingVolume;

    private String per;

    private String roe;

    private String url;

    public Stock toEntity() {
        return Stock.builder()
                .name(this.name)
                .currentPrice(this.currentPrice)
                .fullTime(this.fullTime)
                .fluctuationRate(this.fluctuationRate)
                .faceValue(this.faceValue)
                .marketCap(this.marketCap)
                .shares(this.shares)
                .perForeigners(this.perForeigners)
                .tradingVolume(this.tradingVolume)
                .per(this.per)
                .roe(this.roe)
                .url(this.url)
                .build();
    }

}
