package com.lzy.chatmovie.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.UUID;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtils {
    //FTP文件上传
    public static String upload(String hostname, String username, String password,
                                String targetPath, String suffix, InputStream inputStream) throws SocketException, IOException {
        //实例化ftpClient
        FTPClient ftpClient = new FTPClient();
        //设置登陆超时时间,默认是20s
        ftpClient.setDataTimeout(12000);
        //1.连接服务器
        ftpClient.connect(hostname,21);
        //2.登录（指定用户名和密码）
        boolean b = ftpClient.login(username,password);
        if(!b) {
            System.out.println("登陸超時");
            if (ftpClient.isConnected()) {
                // 断开连接
                ftpClient.disconnect();
            }
        }
        // 设置字符编码
        ftpClient.setControlEncoding("UTF-8");
        //基本路径，一定存在
        String basePath="/";
        String[] pathArray = targetPath.split("/");
        for(String path:pathArray){
            basePath+=path+"/";
            //3.指定目录 返回布尔类型 true表示该目录存在
            boolean dirExsists = ftpClient.changeWorkingDirectory(basePath);
            //4.如果指定的目录不存在，则创建目录
            if(!dirExsists){
                //此方式，每次，只能创建一级目录
                boolean flag=ftpClient.makeDirectory(basePath);
                if (flag){
                    System.out.println("创建成功！");
                }
            }
        }
        //重新指定上传文件的路径
        ftpClient.changeWorkingDirectory(targetPath);
        //5.设置上传文件的方式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //使用uuid，保存文件名唯一性
        String uuid= UUID.randomUUID().toString();
        /**
         * 6.执行上传
         * remote 上传服务后，文件的名称
         * local 文件输入流
         * 上传文件时，如果已经存在同名文件，会被覆盖
         */
        boolean uploadFlag = ftpClient.storeFile(uuid+suffix,inputStream);
        if(uploadFlag)
            System.out.println("上传成功！");
        return uuid+suffix;
    }

    }