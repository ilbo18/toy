package com.example.stock.controller;

import com.example.stock.domain.Stock;
import com.example.stock.dto.StockDto;
import com.example.stock.repository.StockRepository;
import com.netflix.discovery.converters.Auto;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.jsoup.internal.StringUtil.isNumeric;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    StockRepository stockRepository;

    @GetMapping("/")
    public String healthCheck() {return "stockHealthCheck";}

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

    @GetMapping("test")
    public void test() throws IOException {
        String crawlerUrl = "https://finance.naver.com/sise/sise_market_sum.nhn?&page=1";
        Connection conn = Jsoup.connect(crawlerUrl);

        // 페이지 url html 태그 가져오기
        Document document = conn.get();

        // 테이블 td 데이터(row Data)
        Elements stockTableBody = document.select("table.type_2 tbody tr");
        List<Stock> list = new ArrayList<>();


        for(Element element : stockTableBody) {
            if (element.attr("onmouseover").isEmpty()) {
                continue;
            }
            list.add(convertDto(element.select("td")));
        }

        // DB에 Insert
        //stockRepository.saveAll(list);

        list.forEach(System.out::println);

    }
}
