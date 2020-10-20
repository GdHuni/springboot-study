package com.example.demo.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class SpiderTest {

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://qq.ip138.com/idsearch/index.asp?userid=230128199408151220&action=idcard").get();
        String text = document.text();
        Document parse = Jsoup.parse(text);

    }

}
