/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.agt.pojo;

import java.util.Map;

/**
 *
 * @author abd_salam_shaikh
 */
public class FileInfo{
private String fileName;    
private int count;
private long fileSize;
private Map<String, Integer> wordFrequency;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return the wordFrequency
     */
    public Map<String, Integer> getWordFrequency() {
        return wordFrequency;
    }

    /**
     * @param wordFrequency the wordFrequency to set
     */
    public void setWordFrequency(Map<String, Integer> wordFrequency) {
        this.wordFrequency = wordFrequency;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public FileInfo clone() throws CloneNotSupportedException {
        FileInfo file =new FileInfo();
        file.setCount(count);
        file.setFileName(fileName);
        file.setFileSize(fileSize);
        file.setWordFrequency(wordFrequency);
        return file;
    }

}
