package de.agt.assignment.controller;

import de.agt.assignment.service.FileService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class FileInfoController {
    FileService service = new FileService();
    @RequestMapping("/fileInfo")
    public String getFileInfo(@RequestParam(value="path", defaultValue="World") String path) {
        return service.getFileData(path);
    }
    
}
