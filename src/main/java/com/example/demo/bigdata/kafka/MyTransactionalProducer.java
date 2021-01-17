package com.example.demo.bigdata.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

/***
 * 案例1：单个Producer，使用事务保证消息的仅一次发送：
 */
public class MyTransactionalProducer {
    public static void main(String[] args) {
        Map<String,Object> config = new HashMap();
        //kafka服务器地址
        config.put("bootstrap.servers","linux121:9092");
        // key和value的序列化
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        //提供客户端id
        config.put(ProducerConfig.CLIENT_ID_CONFIG,"tx_producer1");
        //事务id
        config.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"my_tx_id1");
        //要求ISR全部确认
        config.put(ProducerConfig.ACKS_CONFIG,"all");

        KafkaProducer<String,String> producer = new KafkaProducer(config);
        //初始化事务
        producer.initTransactions();

        //启动事务
        producer.beginTransaction();
        try{
            producer.send(new ProducerRecord("tp_tx_01", "tx_msg_01"));
            //这里是业务代码  出错的时候回滚事务
            // int i = 1/0;
            //提交事务
            producer.commitTransaction();
        }catch (Exception e){
            e.printStackTrace();
            //回滚事务
            producer.abortTransaction();
        }finally {
            producer.close();
        }

    }
}
