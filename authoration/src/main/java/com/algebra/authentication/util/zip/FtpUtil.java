package com.algebra.authentication.util.zip;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author al
 * @date 2021/7/23 11:45
 * @description
 */
@Slf4j
public class FtpUtil {

    /**
     * 连接ftp服务器
     *
     * @param ip       ftp地址
     * @param port     端口
     * @param username 账号
     * @param password 密码
     * @return
     * @throws IOException
     */
    public static FTPClient ftpConnection(String ip, String port, String username, String password) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, Integer.parseInt(port));
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode();
            //是否成功登录服务器
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.disconnect();
                log.error("--ftp连接失败--");
                System.exit(1);
            }
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }

    /**
     * 简单上传操作
     *
     * @param ftpClient  ftpClient
     * @param remotePath FTP服务器地址：注意一定要写完整的文件路径名以及需要采用绝对路径方式来写
     * @param localPath  本地文件地址
     * @throws IOException
     */
    public static boolean upload(FTPClient ftpClient, String remotePath, String localPath) throws IOException {
        remotePath = new String(remotePath.getBytes("GBK"), StandardCharsets.ISO_8859_1);
        return ftpClient.storeFile(remotePath, new FileInputStream(localPath));
    }

    /**
     * 流上传
     *
     * @param ftpClient  ftpClient
     * @param remotePath FTP服务器地址：注意一定要写完整的文件路径名以及需要采用绝对路径方式来写
     * @param localPath  本地文件地址
     */
    public static void uploadStream(FTPClient ftpClient, String remotePath, String localPath) throws IOException {
        remotePath = new String(remotePath.getBytes("GBK"), StandardCharsets.ISO_8859_1);
        OutputStream os = ftpClient.storeFileStream(remotePath);
        FileInputStream fis = new FileInputStream(localPath);
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = fis.read(b)) != -1) {
            os.write(b, 0, len);
        }
        fis.close();
        os.close();
    }

    /**
     * 断开FTP服务器连接
     *
     * @param ftpClient 初始化的对象
     * @throws IOException
     */
    public static void close(FTPClient ftpClient) throws IOException {
        if (ftpClient != null && ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }


    /**
     * 下载ftp服务器文件方法
     *
     * @param ftpClient     FTPClient对象
     * @param localFileName 新文件名
     * @param remoteFile    原文件（路径＋文件名）
     * @param downUrl       下载路径(本地路径)
     * @return
     * @throws IOException
     */
    public static boolean downFile(FTPClient ftpClient, String localFileName, String remoteFile, String downUrl) throws IOException {
        boolean isTrue = false;
        OutputStream os = null;
        File localFile = new File(downUrl + "/" + localFileName);
        if (!localFile.getParentFile().exists()) {
            //文件夹目录不存在创建目录
            localFile.getParentFile().mkdirs();
            localFile.createNewFile();
        }
        os = new FileOutputStream(localFile);
        isTrue = ftpClient.retrieveFile(new String(remoteFile.getBytes(StandardCharsets.ISO_8859_1), "GBK"), os);
        os.close();
        return isTrue;
    }

    /***
     * 上传文件夹
     * @param localDir 当地文件夹
     * @param remoteDir  Ftp 服务器路径 以目录"/"结束
     * */
    public static boolean uploadDir(FTPClient ftpClient, String localDir, String remoteDir) throws IOException {
        File src = new File(localDir);
//        remoteDir = remoteDir + "/" + src.getName();
        if (!ftpClient.changeWorkingDirectory(remoteDir)) {
            // 文件切换不了说明不存在
            boolean changeDir = ftpClient.changeWorkingDirectory("/");
            boolean createDir = createDir(ftpClient, remoteDir);
            log.info("切换根目录：{}，创建新目录：{}", changeDir, createDir);
        }
        File[] allFile = src.listFiles();
        if (allFile != null) {
            for (File file : allFile) {
                if (!file.isDirectory()) {
                    String srcPath = file.getPath();
                    boolean upload = upload(ftpClient, remoteDir + "/" + file.getName(), srcPath);
//                    System.out.println(upload);
                }
            }
            for (File file : allFile) {
                if (file.isDirectory()) {
                    uploadDir(ftpClient, file.getPath(), remoteDir);
                }
            }
        }
        return true;
    }

    /**
     * 下载文件夹
     *
     * @param localDirectory  本地地址
     * @param remoteDirectory 远程文件夹
     */
    public static boolean downloadDir(FTPClient ftpClient, String localDirectory, String remoteDirectory) {
        try {
            String fileName = new File(remoteDirectory).getName();
            localDirectory = localDirectory + "/" + fileName + "/";
            new File(localDirectory).mkdirs();
            FTPFile[] allFile = ftpClient.listFiles(remoteDirectory);
            for (FTPFile ftpFile : allFile) {
                if (!ftpFile.isDirectory()) {
                    downFile(ftpClient, ftpFile.getName(), remoteDirectory, localDirectory);
                }
            }
            for (FTPFile ftpFile : allFile) {
                if (ftpFile.isDirectory()) {
                    String path = remoteDirectory + "/" + ftpFile.getName();
                    downloadDir(ftpClient, localDirectory, path);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.info("下载文件夹失败");
            return false;
        }
        return true;
    }


    public static boolean createDir(FTPClient ftpClient, String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!"/".equalsIgnoreCase(directory) && !ftpClient.changeWorkingDirectory(directory)) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            StringBuilder paths = new StringBuilder();
            while (true) {

                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), StandardCharsets.ISO_8859_1);
                path = path + "/" + subDirectory;
                if (!existFile(ftpClient, path)) {
                    if (ftpClient.makeDirectory(subDirectory)) {
                        ftpClient.changeWorkingDirectory(subDirectory);
                    } else {
                        log.debug("创建目录[" + subDirectory + "]失败");
                        ftpClient.changeWorkingDirectory(subDirectory);
                    }
                } else {
                    ftpClient.changeWorkingDirectory(subDirectory);
                }

                paths.append("/").append(subDirectory);
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    public static boolean existFile(FTPClient ftpClient, String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    public static void main(String[] args) {
        FTPClient ftpClient = null;
        try {
            ftpClient = FtpUtil.ftpConnection("192.168.6.63", "21", "algebra-ftp", "algebra-ftp");
            System.out.println(ftpClient);

            String status = ftpClient.getStatus();
            System.out.println(status);

            FTPFile[] ftpFiles = ftpClient.listDirectories("/");
            System.out.println(Arrays.toString(ftpFiles));

            /* 批量下载（文件夹下所有文件） */
//            downloadDir(ftpClient, "D:/test01", "/opt");

            /* 批量上传（文件夹下所有文件） */
            uploadDir(ftpClient, "D:\\test01\\root\\file", "/root/file");

            /* 中文上传失败问题 */
//            String remotePath = "/opt/pic/轮巡壁纸 (2).jpg";
//            remotePath = new String(remotePath.getBytes("GBK"), StandardCharsets.ISO_8859_1);
//            boolean upload = upload(ftpClient, remotePath, "D:/test/pic/轮巡壁纸 (2).jpg");
//
//            System.out.println(upload);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                FtpUtil.close(ftpClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
