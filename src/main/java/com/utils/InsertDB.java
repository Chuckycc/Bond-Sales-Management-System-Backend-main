package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.concurrent.Callable;

public class InsertDB implements Callable<Object> {
    // private static String fielsTeminator = "','";
    // private static String linesTeminator = "'\r\n'";
    public String fileName;

    public InsertDB(String fileName) {
        fileName = fileName.replace("\\", "/");
        this.fileName = fileName;
    }

    public Object call() throws Exception {
        System.out.println(" 正在处理：" + fileName + " 线程名：" + Thread.currentThread().getName());
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "666666");
        ) {
            conn.setAutoCommit(false);
            String exectuteSql = "load data infile " + "'" + fileName + "'" + " into table salesRecord ";
            exectuteSql = exectuteSql + " fields terminated by ',' lines terminated by '\r\n' ";
            exectuteSql = exectuteSql + " (bond_name, sales_name, amount, created_time) ";
            try (
                    PreparedStatement pstmt = conn.prepareStatement(exectuteSql)
            ) {
                pstmt.execute();
            }
            conn.commit();
        } catch (Exception e) {
            throw e;
        }
        System.out.println(fileName + "任务结束");
        return "success";
    }
}
