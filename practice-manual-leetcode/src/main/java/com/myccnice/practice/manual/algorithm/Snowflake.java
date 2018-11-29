package com.myccnice.practice.manual.algorithm;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * 1.一个ID由64位生成
 * 2.41bit作为时间戳，记录当前时间到标记的起始时间（如到2018.1.1）差，精确到毫秒，那么服务可用时长为(1<<41)/(1000* 60 * 60 * 24 *365) = 69.73年
 * 3.10bit作为机器ID，也就是可以有1024台机器
 * 4.12bit作为序列号，代表单位时间（这里是毫秒）内允许生成的ID总数，也就是1ms内允许生成4096个ID
 * 
 * 1、取用54位，因为53（高位为0）位最大值为9007199254740991，超过54位生成的id转化成10进制将超管16位，JS处理时会溢出。
 * 0-1111111111-1111111111-1111111111-1111111111-1111111111-111
 * 2、取高41位为时间戳，大概可用70年
 * 3、取次高4位为机器位，最多部署15台机器（足够）
 * 4、取低8为为序列号，最大255，即每毫秒255个序列号(最大255* 15 = 3,825)
 * 
 *
 * @author 王鹏
 * @date 2018年11月27日
 */
public class Snowflake {

    public static void main(String[] args) throws SocketException, UnknownHostException {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (final UnknownHostException e) {
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!");
        }
        byte[] arr = address.getAddress();
        for (byte b : arr) {
            System.out.println(b);
        }
        long workerId = ((arr[arr.length - 2] & 0B11) << Byte.SIZE) + (arr[arr.length - 1] & 0xFF);
        System.out.println(workerId);
        getIpAdd();
    }

    public static void getIpAdd() throws SocketException, UnknownHostException{
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
            NetworkInterface intf = en.nextElement();
            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                //获得IP
                InetAddress inetAddress = enumIpAddr.nextElement();
                if (!inetAddress.isLoopbackAddress()) {
                    String ipaddress = inetAddress.getHostAddress().toString();
                    if (!ipaddress.contains(":") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                        System.out.println(ipaddress);
                    }
                }
            }
        }
    }
}
