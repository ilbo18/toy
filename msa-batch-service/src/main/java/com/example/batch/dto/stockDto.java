package com.example.batch.dto;

import com.example.batch.domain.Stock;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class stockDto {

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
