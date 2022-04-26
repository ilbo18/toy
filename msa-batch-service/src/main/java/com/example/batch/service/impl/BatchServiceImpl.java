package com.example.batch.service.impl;

import com.example.batch.domain.Stock;
import com.example.batch.repository.StockRepository;
import com.example.batch.service.BatchService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.jsoup.internal.StringUtil.isNumeric;

@Service
public class BatchServiceImpl implements BatchService {

    // [Batch Config]
    // <Read -> Process -> Writer>
    // Read -> Stock Data Crawling(읽어오기)
    // Todo: proceess 생략
    @Override
    public List<Stock> stockDataCreate() throws IOException {
        List<Stock> items = new ArrayList<>();

        String crawlerUrl = "https://finance.naver.com/sise/sise_market_sum.nhn?&page=1";
        Connection conn = Jsoup.connect(crawlerUrl);

        // 페이지 url html 태그 가져오기
        Document document = conn.get();

        // 테이블 td 데이터(row Data)
        Elements stockTableBody = document.select("table.type_2 tbody tr");

        for(Element element : stockTableBody) {
            if (element.attr("onmouseover").isEmpty()) {
                continue;
            }
            items.add(convertDto(element.select("td")));
        }

        //list.forEach(System.out::println);
        return items;
    }

    public Stock convertDto(Elements td) throws IOException {

        // 리플렉션을 활용해 객체로 변환
        // Todo : Replection?
        Stock stock = Stock.builder().build(); // 기본 객체 하나 생성
        Class<?> clazz = stock.getClass(); // 해당객체의 Class 타입으로 접근
        Field[] fields = clazz.getDeclaredFields(); // 전체 필드를 배열로 가져오기

        for(int i=0; i<td.size(); i++) {
            Object text;
            if(td.get(i).select(".center a").attr("href").isEmpty()) {
                String str = td.get(i).text().replace(",", "");
                // Long 타입 데이터
                if(!str.contains(".") && isNumeric(str)) {
                    text = Long.parseLong(str);
                }
                // String 타입 데이터
                else {
                    text = td.get(i).text().replace("%", "").replace("+", "");
                }
                // DB URL 컬럼과 맵핑되는 데이터
            } else {
                text = "https://finance.naver.com" + td.get(i).select(".center a").attr("href");
            }
            fields[i].setAccessible(true);

            try{
                fields[i].set(stock, text);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return stock;
    }
}
