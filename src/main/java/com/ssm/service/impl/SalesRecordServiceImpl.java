package com.ssm.service.impl;

import com.ssm.model.SalesRecord;
import com.ssm.dao.SalesRecordDao;
import com.ssm.service.SalesRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class SalesRecordServiceImpl implements SalesRecordService {


    @Autowired
    private SalesRecordDao salesRecordDao;

    @Override
    public void insertRecord(SalesRecord salesRecord) {
        salesRecordDao.insertSelective(salesRecord);
    }

    @Override
    public void deleteRecord(Integer id) {
        salesRecordDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateRecord(SalesRecord salesRecord) {
        salesRecordDao.updateByPrimaryKeySelective(salesRecord);
    }

    @Override
    public SalesRecord getRecordById(Integer id) {
        return salesRecordDao.getRecordById(id);
    }

    @Override
    public List<SalesRecord> getAllRecords() {
        return salesRecordDao.getAllRecords();
    }

    // @Override
    // public void loadCSVData(String csvFile, String fieldTerminator, String lineTerminator) {
    //     salesRecordDao.loadCSVData(csvFile, fieldTerminator, lineTerminator);
    //     // sqlSession.commit();
    // }
    //
    // @Override
    // public void loadCSVData() {
    //     salesRecordDao.loadTest();
    // }

    public List<SalesRecord> selectByCondition(Integer id, String bond_name, String sales_name, Integer amount, LocalDate begin_time, LocalDate end_time){
        return salesRecordDao.selectByCondition(id,bond_name,sales_name,amount,begin_time,end_time);
    }

}

