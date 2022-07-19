package com.ssm.service;

import com.ssm.model.SalesRecord;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface SalesRecordService {

    void insertRecord(SalesRecord salesRecord);

    void deleteRecord(Integer id);

    void updateRecord(SalesRecord salesRecord);

    SalesRecord getRecordById(Integer id);

    List<SalesRecord> getAllRecords();

    // void loadCSVData(String csvFile, String fieldTerminator, String lineTerminator);

    // void loadCSVData();
    // Collection<SalesRecord> getAllRecordsByCondition();

    List<SalesRecord> selectByCondition(Integer id, String bond_name, String sales_name, Integer amount, LocalDate begin_time, LocalDate end_time);

}
