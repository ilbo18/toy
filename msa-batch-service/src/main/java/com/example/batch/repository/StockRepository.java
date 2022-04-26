package com.example.batch.repository;

import com.example.batch.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StockRepository extends JpaRepository<Stock, Stock> {

}
