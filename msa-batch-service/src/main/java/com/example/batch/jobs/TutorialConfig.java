package com.example.batch.jobs;

import com.example.batch.config.CustomItemReader;
import com.example.batch.domain.Stock;
import com.example.batch.repository.StockRepository;
import com.example.batch.service.BatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TutorialConfig {

    private final JobBuilderFactory jobBuilderFactory; // Job 빌더 생성용
    private final StepBuilderFactory stepBuilderFactory; // Step 빌더 생성용
    private final BatchService batchService;

    @Autowired
    private final StockRepository stockRepository;

    // JobBuilderFactory를 통해서 tutorialJob을 생성
    @Bean
    public Job tutorialJob() throws IOException {
        return jobBuilderFactory.get("stockDataInsertJob")
                .incrementer(new RunIdIncrementer())
                .start(this.customItemReaderStep())  // Step 설정
                .build();
    }

    @Bean
    public Step customItemReaderStep() throws IOException {
        return this.stepBuilderFactory.get("customItemReaderStep")
                .<Stock, Stock>chunk(10)
                .reader(new CustomItemReader<>(batchService.stockDataCreate()))
                .writer(customItemWriter())
                .allowStartIfComplete(true)
                .build();
    }

    // [Batch Config]
    // <Read -> Process -> Writer>
    // Writer -> 읽어온 데이터 저장(저장하기, Stock 데이터베이스)
    // msa 바라보는 데이터 베이스가 다르기 때문에 API 통신으로 Stock 서비스와 통신
    private ItemWriter<Stock> customItemWriter() {
//        return items -> log.info(items.stream()
//                .map(Stock::getName)
//                .collect(Collectors.joining(",")));

        return new ItemWriter<Stock>() {
            @Override
            public void write(List<? extends Stock> items) throws Exception {
                stockRepository.saveAll(items); // DB에 Insert
            }
        };
    }
}
