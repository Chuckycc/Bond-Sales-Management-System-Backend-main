package com.ssm.service.impl;

import com.ssm.dao.SalesRecordDao;
import com.ssm.service.LoadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LoadDataServiceImpl implements LoadDataService {

    private static final int linesPerFile = 100000;
    private static final Logger logger = LoggerFactory.getLogger(LoadDataServiceImpl.class);
    private static final String fieldsTerminator = ",";
    private static final String linesTerminator = "\r\n";

    @Autowired
    SalesRecordDao salesRecordDao;

    public int splitFile(String inFile, String outDir) throws Exception {

        inFile = inFile.replace("\\", "/");
        outDir = outDir.replace("\\", "/");

        FileReader fileReader = new FileReader(inFile);
        int numFiles = 0;
        try (BufferedReader bufferedReader = new BufferedReader((fileReader))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String outFileName = "";
                outFileName = Paths.get(outDir, numFiles + ".csv").toString();
                try (FileWriter fileWriter = new FileWriter(outFileName)) {
                    fileWriter.append(row + "\r\n");
                    for (int i = 1; i < linesPerFile; i++) {
                        if ((row = bufferedReader.readLine()) == null) {
                            break;
                        }
                        fileWriter.append(row + "\r\n");
                    }
                    numFiles++;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return numFiles;
    }

    @Async
    public void loadData(String fileName) throws Exception {
        fileName = fileName.replace("\\", "/");
        // String threadName = Thread.currentThread().getName();
        // System.out.println("线程名:" + threadName+ " 正在处理: " + fileName);
        logger.info("正在处理: " + fileName);

        salesRecordDao.loadCSVData(fileName, fieldsTerminator, linesTerminator);
        // System.out.println("线程名:" + threadName+ " 任务结束: " + fileName);
        logger.info("任务结束: " + fileName);
    }
}
