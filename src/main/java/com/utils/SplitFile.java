package com.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import java.nio.file.Paths;

public class SplitFile {

    private final String inFile;
    private final String outDir;

    public SplitFile(String inFile, String outDir){
        this.inFile = inFile;
        this.outDir = outDir;
    }

    public int split(int linesPerFile) throws Exception{
        FileReader fileReader = new FileReader(inFile);
        int numFiles = 0;
        try (BufferedReader bufferedReader = new BufferedReader((fileReader)))
        {
            String row;
            while((row = bufferedReader.readLine()) != null){
                String outFileName = "";
                outFileName = Paths.get(outDir, numFiles+".csv").toString();
                try (FileWriter fileWriter = new FileWriter(outFileName)) {
                    fileWriter.append(row+"\r\n");
                    for(int i = 1; i < linesPerFile ; i++){
                        if((row = bufferedReader.readLine()) == null){
                            break;
                        }
                        fileWriter.append(row+"\r\n");
                    }
                    numFiles++;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return numFiles;
    }

    // public static void main(String[] args) throws Exception {
    //     SplitFile splitFile = new SplitFile(inFile);
    //     int numFiles = splitFile.split();
    //     System.out.println("success: " + numFiles + " files");
    // }
}
