package com.ssm.controller;

import com.ssm.service.LoadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Paths;
import java.nio.file.Path;

@RestController
public class UploadFileController {

    @Autowired
    private LoadDataService loadDataService;

    @PostMapping(value="api/upload")
    public ResponseEntity<Object> processUpload(@RequestParam(value = "file") MultipartFile file) throws Exception {

        String rootPath = System.getProperty("user.dir");
        String outDirName = "csvFiles";
        String outFileName = "salesRecords.csv";
        Path dirPath = Paths.get(rootPath, outDirName);
        Path filePath = Paths.get(rootPath, outDirName, outFileName);
        file.transferTo(filePath.toFile());

        // async
        // Run.execMultiThread(filePath.toString(), dirPath.toString());
        int numFiles = loadDataService.splitFile(filePath.toString(), dirPath.toString());
        for (int i = 0; i < numFiles; i++) {
            String tmpFileName = Paths.get(dirPath.toString(), i+".csv").toString();
            loadDataService.loadData(tmpFileName);
        }

        return new ResponseEntity<> ("upload success",HttpStatus.OK);
    }
}
