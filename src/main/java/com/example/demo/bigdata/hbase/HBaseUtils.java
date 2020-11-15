package com.example.demo.bigdata.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

public class HBaseUtils {
    Configuration conf=null;
    Connection conn=null;
    HBaseAdmin admin =null;
    public HBaseUtils(){
        //创建配置文件
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","192.168.111.121,192.168.111.122");
        try {
            //创建连接
            conn = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Javaapi创建HBase表
     * @param tableName 表名
     * @param familyName 列族
     */
    public void createTable(String tableName,String familyName){
        try {
            admin = (HBaseAdmin) conn.getAdmin(); //创建表描述器
            HTableDescriptor teacher = new HTableDescriptor(TableName.valueOf(tableName)); //设置列族描述器
            teacher.addFamily(new HColumnDescriptor(familyName)); //执行创建操作
            admin.createTable(teacher); System.out.println(tableName+"表创建成功！！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
