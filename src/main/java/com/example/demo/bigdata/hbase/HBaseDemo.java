package com.example.demo.bigdata.hbase;

public class HBaseDemo {
    private static HBaseUtils hBaseUtils = new HBaseUtils();
    public static void main(String[] args) {
      hBaseUtils.createTable("user_relation","friends");

    }
}
