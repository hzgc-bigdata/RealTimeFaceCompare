package com.hzgc.ftpserver.address;

import com.hzgc.dubbo.address.FtpAddressService;
import com.hzgc.ftpserver.local.LocalOverFtpServer;
import com.hzgc.util.FileUtil;
import com.hzgc.util.IOUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

/**
 * 供平台获取FTP Server信息，返回当前连接数最少的FTP Server
 */
public class FtpAddressServiceImpl implements FtpAddressService, Serializable {
    private static Logger log = Logger.getLogger(FtpAddressServiceImpl.class);
    private static Properties proper = new Properties();

    public FtpAddressServiceImpl() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(FileUtil.loadResourceFile("ftpAddress.properties"));
            proper.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeStream(fis);
        }
        String ipRet = getUsefulIP(proper);
        proper.setProperty("ip", ipRet);
        System.out.println(proper.getProperty("ip"));
    }

    @Override
    public Properties getFtpAddress() {
        return proper;
    }

    private static String getUsefulIP(Properties props) {
        String ipAddr = props.getProperty("ip");
        String[] ipAddressArr = ipAddr.split(",");
        Map<String, Integer> ipConnMap = new HashMap<>(ipAddressArr.length);
        int port = Integer.parseInt(props.getProperty("port"));
        String username = props.getProperty("user");
        String password = props.getProperty("password");
        for (String ip : ipAddressArr) {
            FTPClient client = new FTPClient();
            try {
                client.connect(ip, port);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                client.login(username, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //获取当前FTP服务器统计信息
                client.sendCommand("SITE STAT");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] siteReplies = client.getReplyString().split("\r\n");
            //siteReplies[13]为当前服务器连接数
            String[] currentConnNum = siteReplies[13].split(":");
            int connectionNum = Integer.parseInt(currentConnNum[1].trim());
            log.info("当前IP地址：" + ip + "连接数：" + connectionNum);
            ipConnMap.put(ip, connectionNum);
        }
        return sortByValue(ipConnMap).entrySet().iterator().next().getKey();
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();
        st.sorted(Comparator.comparing(Map.Entry::getValue)).forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

}
