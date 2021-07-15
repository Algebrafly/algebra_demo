package com.algebra.authentication.util.mysql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author al
 * @date 2021/7/15 11:55
 * @description
 */
public class TestBinLog {

    public static void main(String[] args) {

        BinaryLogClient client = new BinaryLogClient("127.0.0.1", 3306, "root", "123456");
        client.setServerId(2);

        client.registerEventListener(event -> {
            EventData data = event.getData();
            if (data instanceof TableMapEventData) {
                System.out.println("Table:");
                TableMapEventData tableMapEventData = (TableMapEventData) data;
                System.out.println(tableMapEventData.getTableId()+": ["+tableMapEventData.getDatabase() + "-" + tableMapEventData.getTable()+"]");
            }
            if (data instanceof UpdateRowsEventData) {
                System.out.println("Update:");
                System.out.println(data.toString());
                UpdateRowsEventData updateRowsEventData = (UpdateRowsEventData) data;
                for (Map.Entry<Serializable[], Serializable[]> row : updateRowsEventData.getRows()) {
                    List<Serializable> entries = Arrays.asList(row.getValue());
                    System.out.println(entries);
                    JSONObject dataObject = getDataObject(entries);
                    System.out.println(dataObject);
                }
            } else if (data instanceof WriteRowsEventData) {
                System.out.println("Insert:");
                System.out.println(data.toString());
            } else if (data instanceof DeleteRowsEventData) {
                System.out.println("Delete:");
                System.out.println(data.toString());
            }
        });

        try {
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static JSONObject getDataObject(List message) {
        JSONObject resultObject = new JSONObject();
        String format = "{\"book_no\":\"0\",\"name\":\"1\",\"price\":\"2\",\"publication_date\":\"3\"}";
        JSONObject json = JSON.parseObject(format);
        for (String key : json.keySet()) {
            resultObject.put(key, message.get(json.getInteger(key)));
        }
        return resultObject;
    }
}
