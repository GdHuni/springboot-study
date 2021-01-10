package com.example.demo.bigdata.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.HashMap;
import java.util.Map;

public class KafkaDemo1 {
    public static void main(String[] args) {
        Map<String,Object> config = new HashMap();
        config.put("bootstrap.servers","linux121:9092");
        KafkaProducer producer = new KafkaProducer(config);
    }
}
