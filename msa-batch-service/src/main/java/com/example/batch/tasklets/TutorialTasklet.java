package com.example.batch.tasklets;

import com.example.batch.repository.StockRepository;
import com.example.batch.service.BatchService;
import com.example.batch.service.impl.BatchServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.ArrayList;

@Slf4j
public class TutorialTasklet implements Tasklet, StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("BeforeStep~~~~~~~");
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        //batchService.stockDataCreate();
        System.out.println("execute~~~~~");

        return RepeatStatus.FINISHED;
    }



    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("AfterStep~~~~~~~");
        return null;
    }
}
