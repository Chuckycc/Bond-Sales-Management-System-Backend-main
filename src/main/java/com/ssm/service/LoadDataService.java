package com.ssm.service;

public interface LoadDataService {

    int splitFile(String inFile, String outDir) throws Exception;

    void loadData(String fileName) throws Exception;

}
