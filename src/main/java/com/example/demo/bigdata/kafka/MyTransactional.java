package com.example.demo.bigdata.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 在 消费-转换-生产 模式，使用事务保证仅一次发送。
 */
public class MyTransactional {

    public static void main(String[] args) {
        //
        KafkaProducer producer = getKafkaProducer();
        //
        KafkaConsumer consumer = getKafkaConsumer();
        // 事务的初始化
        producer.initTransactions();

        //订阅主题
        consumer.subscribe(Collections.singleton("tp_tx_01"));
        //拉去消息
        ConsumerRecords<String, String> records = consumer.poll(1_000);
        //开启事务
        producer.beginTransaction();

        try{
            Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<> ();
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
                producer.send(new ProducerRecord<String, String>("tp_tx_out_01", record.key(), record.value()));
                // 偏 移量表示下一条要消费的消息
                offsets.put( new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1));
            }
            // 将该消息的偏移量提交作为事务的一部分，随事务提交和回滚（不提交消费偏移 量）
            // 这里是说我这个消费者不提交偏移量，所以我重试的时候还是能从消费前的那个偏移量开始消费，所以还能拿到主题中的数据并且给下面的生产者发送出去
            producer.sendOffsetsToTransaction(offsets, "consumer_txgrp_00");
          //   int i = 1 / 0;
            // 提交事务
            producer.commitTransaction();
        }catch (Exception e){
            //回滚事务
            //如果想要在事务回滚后，tp_tx_out_01 主题消费不到回滚前的数据，需要在消费的时候指定只消费事务已提交的消息 --isolation-level read_committed （默认是read_uncommitted）
            producer.abortTransaction();
            e.printStackTrace();
        }finally {
            // 关闭资源
            producer.close();
            consumer.close();
        }

    }

    /**
     * 获取kafka生产者
     * @return
     */
    public static KafkaProducer getKafkaProducer(){
        Map<String, Object> config = new HashMap<>();
        //kafka服务器地址
        config.put("bootstrap.servers","linux121:9092");
        // key和value的序列化
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        //提供客户端id
        config.put(ProducerConfig.CLIENT_ID_CONFIG,"tx_producer0");
        //事务id
        config.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"my_tx_id0");
        //要求ISR全部确认
        config.put(ProducerConfig.ACKS_CONFIG,"all");
        // 启用幂等性
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(config);
        return producer;
    }
    /**
     * 获取kafka消费者
     */

    public static KafkaConsumer getKafkaConsumer(){
        Map config = new HashMap();
        //连接kafka集群
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"linux121:9092");
        //消费者反序列化
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        //消费者组id
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"consumer_txgrp_00");
        //不启用消费者偏移量的自动确认，也不要手动确认
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        //客户端id
        config.put(ConsumerConfig.CLIENT_ID_CONFIG,"consumer_txclient_0");
        //当没有主题分区中的偏移量时候，从哪里的偏移量开始消费
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        // 只读取已提交的消息
        // configs.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");

        KafkaConsumer consumer = new KafkaConsumer(config);

        return consumer;
    }

}
