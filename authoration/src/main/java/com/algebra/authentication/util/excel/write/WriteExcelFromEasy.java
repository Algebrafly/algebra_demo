package com.algebra.authentication.util.excel.write;

import com.algebra.authentication.util.excel.read.DemoData;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author al
 * @date 2020/11/19 15:29
 * @description 【alibaba-EasyExcel】 写Excel
 *  * 参考：https://www.yuque.com/easyexcel/doc/write
 */
public class WriteExcelFromEasy {

    /**
     * 最简单的写
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 直接写即可
     */
    public static void simpleWrite() {
        // 写法1
        String fileName = "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

        // 写法2
//        fileName = "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写
//        ExcelWriter excelWriter = null;
//        try {
//            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
//            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//            excelWriter.write(data(), writeSheet);
//        } finally {
//            // 千万别忘记finish 会帮忙关闭流
//            if (excelWriter != null) {
//                excelWriter.finish();
//            }
//        }
    }


    /**
     * 列宽、行高
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link WidthAndHeightData}
     * <p>
     * 2. 使用注解ColumnWidth、HeadRowHeight、ContentRowHeight指定宽度或高度
     * <p>
     * 3. 直接写即可
     */
    public void widthAndHeightWrite() {
        String fileName = "widthAndHeightWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, WidthAndHeightData.class).sheet("模板").doWrite(data());
    }

    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }


    public static void main(String[] args) {

        simpleWrite();

    }

}
