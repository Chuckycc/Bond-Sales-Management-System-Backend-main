package com.utils;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Run {

    private static final int numThreads = 8;
    private static final int linesPerFile = 100000;
    // private static String fielsTeminator = "','";
    // private static String linesTeminator = "'\r\n'";

    public static void execMultiThread(String inFile, String outDir) throws Exception {

        inFile = inFile.replace("\\", "/");
        outDir = outDir.replace("\\", "/");
        SplitFile splitFile = new SplitFile(inFile, outDir);
        int numFiles = splitFile.split(linesPerFile);
        ExecutorService pool = Executors.newFixedThreadPool(numThreads);
        // 获取所有并发任务的运行结果
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < numFiles; i++) {
            String tmpFileName = Paths.get(outDir, i+".csv").toString();
            Callable c = new InsertDB(tmpFileName);
            Future f = pool.submit(c);
            list.add(f);
        }

        pool.shutdown();
    }

    public static void execSingleThread(String fileName) throws Exception {
        fileName = fileName.replace("\\", "/");
        long startTime = System.currentTimeMillis();
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "666666")
        ) {
            conn.setAutoCommit(false);
            String exectuteSql = "load data non-local infile " + "'" + fileName + "'" + " into table salesRecord ";
            exectuteSql = exectuteSql + " fields terminated by ',' lines terminated by '\r\n' ";
            exectuteSql = exectuteSql + " (bond_name, sales_name, amount, created_time) ";
            try (PreparedStatement pstmt = conn.prepareStatement(exectuteSql)) {
                pstmt.execute();
            }
            conn.commit();
        } catch (Exception e) {
            throw e;
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("总时间：" + totalTime / 1000);
    }

}
