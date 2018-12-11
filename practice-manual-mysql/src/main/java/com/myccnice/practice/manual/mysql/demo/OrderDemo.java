package com.myccnice.practice.manual.mysql.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class OrderDemo {

    private static Random ra =new Random();
    private static long min = 0;
    private static long max = 0;
    private static long startId = 66525973133348L;
    private static String querySql = "SELECT id FROM pub_order_info_66228803295768 WHERE comId = 65975396498968 AND id > ? LIMIT 10000";
    private static String updateSql = "UPDATE pub_order_info_66228803295768 " + 
            " SET saleDate = ?,createTime = saleDate, payTime = saleDate" +
            " WHERE id  BETWEEN ? AND ?;";
    private static Connection conn = DBHerpel.getConnection();
    private static Calendar startDate = Calendar.getInstance();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static {
        startDate.set(2018, Calendar.OCTOBER, 01, 16, 34, 26);
    }

    public static void main(String[] args) throws SQLException {
        while (setIds()) {
            udpate();
        }
    }

    private static boolean setIds() throws SQLException {
        min = 0;
        max = 0;
        PreparedStatement ps = conn.prepareStatement(querySql);
        ps.setLong(1, startId);
        ResultSet rs = ps.executeQuery();
        boolean flag = false;
        while (rs.next()) {
            flag = true;
            long temp = rs.getLong(1);
            if(min == 0 || temp < min) {
                min = temp;
            }
            if (max == 0 || temp > max) {
                max = temp;
            }
        }
        startId = max;
        System.out.print("max=" + max);
        return flag;
    }

    private static void udpate() throws SQLException {
        startDate.add(Calendar.DAY_OF_YEAR, 1);
        startDate.set(Calendar.HOUR_OF_DAY, ra.nextInt(24));
        startDate.set(Calendar.MINUTE, ra.nextInt(60));
        startDate.set(Calendar.SECOND, ra.nextInt(60));
        System.out.println(sdf.format(startDate.getTime()));
        PreparedStatement ps = conn.prepareStatement(updateSql);
        ps.setTimestamp(1, new Timestamp(startDate.getTimeInMillis()));
        ps.setLong(2, min);
        ps.setLong(3, max);
        ps.execute();
    }
}
