/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.agt.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abd_salam_shaikh
 */
public class Directory{
    String name;
    List<Directory> directories;
    List<FileInfo> files;

    public Directory(){
        files = new ArrayList<FileInfo>();
        directories = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(List<Directory> directories) {
        this.directories = directories;
    }

    public List<FileInfo> getFiles() {
        return files;
    }

    public void setFiles(List<FileInfo> files) {
        this.files = files;
    }

    @Override
    public Directory clone() throws CloneNotSupportedException {
        Directory temp = new Directory();
        List<Directory> dirs = new ArrayList();
        for(Directory dir:directories){
            dirs.add(dir.clone());    
        } 
        temp.setDirectories( dirs);
        List<FileInfo> filesTemp= new ArrayList();
        for(FileInfo f:files){
            filesTemp.add(f.clone());    
        }
        temp.setDirectories(dirs);
        temp.setFiles(filesTemp);
        temp.setName(name);
        return  temp;
    }
    
}
