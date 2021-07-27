package com.algebra.authentication.util.zip;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author al
 * @date 2021/7/27 9:38
 * @description
 */
@Slf4j
public class ZipFileUtil {

    private static List<CustomerFile> files = new ArrayList<>();

    private static final FTPClient ftpClient = FtpUtil.ftpConnection("192.168.6.63", "21", "algebra-ftp", "algebra-ftp");

    static {
        CustomerFile f1 = new CustomerFile("A1001", "F001", "F1.jpg", "/root/file/");
        CustomerFile f2 = new CustomerFile("A1001", "F002", "F2.pdf", "/root/file/");
        CustomerFile f3 = new CustomerFile("A1002", "F003", "F3.png", "/root/file/");
        CustomerFile f4 = new CustomerFile("A1002", "F004", "F4.jpg", "/root/file/");
        CustomerFile f5 = new CustomerFile("A1002", "F005", "F5.txt", "/root/file/");
        CustomerFile f6 = new CustomerFile("A1003", "F006", "F6.jpg", "/root/file/");
        CustomerFile f7 = new CustomerFile("A1004", "F007", "F7.docs", "/root/file/");
        CustomerFile f8 = new CustomerFile("A1004", "F008", "F8.jpg", "/root/file/");
        CustomerFile f9 = new CustomerFile("A1004", "F009", "F9.jpg", "/root/file/");
        CustomerFile f10 = new CustomerFile("A1005", "F0010", "F10.xlsx", "/root/file/");
        Collections.addAll(files, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10);
    }

    /**
     * 两种处理方式：
     * 1. 存储的时候就是按照合同分好组的，后续取出来（按照合同文件夹）就可以用
     * 2. 存储时候集中混乱存储，后续按照DB存储的每一个文件的path循环取出，然后放到本地指定文件夹下（按照合同创建）统一使用
     *
     * @param args
     */
    public static void main(String[] args) {

        Map<String, List<CustomerFile>> groupByNo = files.stream().collect(Collectors.groupingBy(CustomerFile::getContractNo));
        log.info("按照合同分组，结果：{}", JSONUtil.toJsonStr(groupByNo));

        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String rootPath = "/root/" + dateStr;
        String localZipPath = rootPath + "/zip/";
        log.info("遍历合同Map，根据合同名称创建文件夹并按照合同分组拉取文件，根目录：{}", rootPath);
        groupByNo.forEach((k, v) -> {
            String contractPath = rootPath + "/" + k;
            FileUtil.mkdir(contractPath);
            log.debug("循环遍历，拉取FTP服务器上文件");
            for (CustomerFile customerFile : v) {
                try {
                    FtpUtil.downFile(ftpClient, customerFile.getName(),
                            customerFile.getPath() + "/" + customerFile.getName(), contractPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            log.info("处理压缩合同：{}下面的文件，path：{}", k, contractPath);
            ZipUtil.zip(contractPath, localZipPath + k + ".zip", true);
        });

        String ftpZipPath = "/root/zip/" + dateStr;
        log.info("压缩处理完毕！上传压缩包到FTP文件指定目录，FTP-path: {}", ftpZipPath);
        try {
            FtpUtil.uploadDir(ftpClient, localZipPath, ftpZipPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Data
    static class CustomerFile {

        private String contractNo;

        private String fid;

        private String name;

        private String path;

        public CustomerFile() {
        }

        public CustomerFile(String contractNo, String fid, String name, String path) {
            this.contractNo = contractNo;
            this.fid = fid;
            this.name = name;
            this.path = path;
        }

    }

}