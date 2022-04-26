package com.example.batch.service;

import com.example.batch.domain.Stock;

import java.io.IOException;
import java.util.List;

public interface BatchService {
     List<Stock> stockDataCreate() throws IOException;
}
