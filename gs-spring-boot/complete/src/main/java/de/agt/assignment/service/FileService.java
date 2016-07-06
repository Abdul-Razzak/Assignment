/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.agt.assignment.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.agt.assignment.pojo.Directory;
import de.agt.assignment.pojo.FileInfo;
import java.io.File;
import java.io.FilenameFilter;

public class FileService {

    // create new filename filter
    FilenameFilter fileNameFilter = new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
            if (name.lastIndexOf('.') > 0) {
                int lastIndex = name.lastIndexOf('.');
                String str = name.substring(lastIndex);
                if (str.equals(".txt")) {
                    return true;
                }
            } else if(dir.isDirectory()){
                return true;
            } 
            return false;
        }
    };

    public void readFiles(File file, Directory dir) {
        dir.setName(file.getName());
        for (File f : file.listFiles(fileNameFilter)) {
            if (f.isDirectory()) {
                Directory dirSub = new Directory();
                readFiles(f, dirSub);
                dir.getDirectories().add(dirSub);
            } else {
                FileInfo info = new FileInfo();
                info.setFileName(f.getName());
                info.setCount(FileUtils.countWords(f.getAbsolutePath()));
                info.setWordFrequency(FileUtils.findRepeatedWords(f.getAbsolutePath())); 
                info.setFileSize(f.length() / 1024);
                dir.getFiles().add(info);
            }
        }
    }

    public String getFileData(String path) {
        File file = new File(path);
        if (!file.isDirectory()) {
            System.out.println("please provide dir");
        }
        Directory dir = new Directory();
        readFiles(file, dir);

        try {
            Directory lower = dir.clone();
            lower.filterFilesByFilterLength(0, 1000);

            Directory high = dir.clone();
            high.filterFilesByFilterLength(1000, -1);
            Gson son = new Gson();

            System.err.println(son.toJson(high));

            System.err.println("--" + son.toJson(lower));

            JsonObject jsonObj = new JsonObject();
            jsonObj.add("Big_File", son.toJsonTree(high).getAsJsonObject());
            jsonObj.add("Short_File", son.toJsonTree(lower).getAsJsonObject());

            return jsonObj.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
;
}
