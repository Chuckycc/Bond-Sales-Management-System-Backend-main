package com.ssm.dao;

import com.ssm.mapper.SalesRecordMapper;
import com.ssm.model.SalesRecord;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface SalesRecordDao extends SalesRecordMapper {

    @Results(id="Record", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "bondName", column = "bond_name"),
            @Result(property = "salesName", column = "sales_name"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "createdTime", column = "created_time")
    })
    @Select("select * from salesRecord where id=#{id}")
    SalesRecord getRecordById(Integer id);

    @ResultMap("Record")
    @Select("select * from salesRecord")
    List<SalesRecord> getAllRecords();


    void loadCSVData(String csvFile, String fieldsTerminator, String linesTerminator);

    List<SalesRecord> selectByCondition(Integer id, String bond_name, String sales_name, Integer amount, LocalDate begin_time, LocalDate end_time);
}
