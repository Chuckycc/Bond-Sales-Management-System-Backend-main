package com.ssm.controller;

import com.ssm.service.SalesRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ssm.model.SalesRecord;

import java.time.LocalDate;
import java.util.*;

@RestController
public class SalesRecordController {

    @Autowired
    private SalesRecordService salesRecordService;

    @GetMapping("/api/get")
    public SalesRecord getRecord(@RequestParam(value = "id", defaultValue = "1") Integer id) {
        // return salesRecordDao.selectByPrimaryKey(id);
        return  salesRecordService.getRecordById(id);
    }

    @GetMapping("/api/records")
    public List<SalesRecord> getAllRecords() {
        return  salesRecordService.getAllRecords();
    }

    @PostMapping(path ="/api/sale")
    public Map<String, Boolean> addRecord(@RequestBody SalesRecord salesRecord) {
        Map<String, Boolean> respondMap = new HashMap<>();
        respondMap.put("success", false);
        salesRecordService.insertRecord(salesRecord);
        respondMap.put("success", true);
        return respondMap;
    }

    @DeleteMapping("api/sale")
    public Map<String, Boolean> deleteRecord(@RequestBody Map<String, List<Integer>> intListMap){
        Map<String, Boolean> respondMap = new HashMap<>();
        respondMap.put("success", false);
        for (int id: intListMap.get("id")) {
            salesRecordService.deleteRecord(id);
        }
        respondMap.put("success", true);
        return respondMap;
    }

    @PutMapping("api/sale")
    public Map<String, Boolean> updateRecord(@RequestBody SalesRecord salesRecord){
        Map<String, Boolean> respondMap = new HashMap<>();
        respondMap.put("success", false);
        salesRecordService.updateRecord(salesRecord);
        respondMap.put("success", true);
        return respondMap;
    }

    @GetMapping("api/sale")
    public Map<String, Object> query(@RequestParam Map<String,Object> paramsMap) {
        Integer id = null;
        if (paramsMap.containsKey("id") ) {
            id = Integer.parseInt((String) paramsMap.get("id"));
        }
        Integer amount = null;
        if (paramsMap.containsKey("amount") ) {
            amount = Integer.parseInt((String) paramsMap.get("amount"));
        }
        String bond_name = null;
        if (paramsMap.containsKey("bondName") ) {
            bond_name = (String) paramsMap.get("bondName");
        }
        String sales_name = null;
        if (paramsMap.containsKey("salesName") ) {
            sales_name = (String) paramsMap.get("salesName");
        }
        LocalDate begin_time = null;
        if (paramsMap.containsKey("beginTime") ) {
            begin_time = LocalDate.parse((String) paramsMap.get("beginTime"));
        }
        LocalDate end_time = null;
        if (paramsMap.containsKey("endTime") ) {
            end_time = LocalDate.parse((String) paramsMap.get("endTime"));
        }
        List<SalesRecord> allRecords = salesRecordService.selectByCondition(
                id, bond_name, sales_name, amount, begin_time, end_time
        );

        Map<String, Object> respondMap = new HashMap<>();
        int totalSize = allRecords.size();
        List<SalesRecord> data = allRecords;
        respondMap.put("data", data);
        respondMap.put("success", true);
        respondMap.put("total", totalSize);
        return respondMap;
    }

    @GetMapping("api/sale2")
    public Map<String, Object> query2(@RequestParam Map<String,Object> paramsMap) {
        Integer id = null;
        if (paramsMap.containsKey("id") ) {
            id = Integer.parseInt((String) paramsMap.get("id"));
        }
        Integer amount = null;
        if (paramsMap.containsKey("amount") ) {
            amount = Integer.parseInt((String) paramsMap.get("amount"));
        }
        String bond_name = null;
        if (paramsMap.containsKey("bondName") ) {
            bond_name = (String) paramsMap.get("bondName");
        }
        String sales_name = null;
        if (paramsMap.containsKey("salesName") ) {
            sales_name = (String) paramsMap.get("salesName");
        }
        LocalDate begin_time = null;
        if (paramsMap.containsKey("beginTime") ) {
            begin_time = LocalDate.parse((String) paramsMap.get("beginTime"));
        }
        LocalDate end_time = null;
        if (paramsMap.containsKey("endTime") ) {
            end_time = LocalDate.parse((String) paramsMap.get("endTime"));
        }
        List<SalesRecord> allRecords = salesRecordService.selectByCondition(
                id, bond_name, sales_name, amount, begin_time, end_time
        );
        int current = Integer.parseInt((String) paramsMap.get("current"));
        int pageSize = Integer.parseInt((String) paramsMap.get("pageSize"));
        Map<String, Object> respondMap = new HashMap<>();
        int totalSize = allRecords.size();
        int startIdx = (current-1)*pageSize;
        int endIdx =  Math.min(totalSize, current*pageSize);
        List<SalesRecord> data = allRecords.subList(startIdx, endIdx);
        respondMap.put("data", data);
        respondMap.put("success", true);
        respondMap.put("total", totalSize);
        respondMap.put("current", current);
        respondMap.put("pageSize", pageSize);
        return respondMap;
    }

}

