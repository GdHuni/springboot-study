package com.example.demo.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class WordCountStreamingByJava {
    public static void main(String[] args) throws Exception {

        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 设置socket数据源
        DataStreamSource<String> source = env.socketTextStream("172.16.3.127", 9997, "\n");
        // 转化处理数据
        DataStream<WordWithCount> dataStream = source.flatMap(new FlatMapFunction<String, WordWithCount>() {
            @Override
            public void flatMap(String line, Collector<WordWithCount> collector) throws Exception {
                for (String word : line.split(" ")) {
                    collector.collect(new WordWithCount(word, 1));
                }
            }
        }).keyBy("word")//以key分组统计
                .timeWindow(Time.seconds(2),Time.seconds(2))//设置一个窗口函数，模拟数据流动
                .sum("count");//计算时间窗口内的词语个数

        // 输出数据到目的端
        dataStream.print();

        // 执行任务操作
        env.execute("Flink Streaming Word Count By Java");

    }

    public static class WordWithCount{
        public String word;
        public int count;

        public WordWithCount(){

        }

        public WordWithCount(String word, int count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return "WordWithCount{" +
                    "word='" + word + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
