package de.agt;

import de.agt.pojo.Directory;
import de.agt.pojo.FileInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String path = "http://localhost:8080/fileInfo?path=C:\\Users\\abdul razzak\\Desktop\\git\\AGT-Assignment\\testdata";
        JsonObject json = restTemplate.getForObject(path,JsonObject.class);        
        Gson gson=new Gson();
        System.out.println(json.toString());
        boolean isFileBigFlag = true;
        System.out.println("Big Files");
        System.out.println(gson.fromJson(json.get("Big_File"),Directory.class));
        Directory longFilesDir = gson.fromJson(json.get("Big_File"),Directory.class);
        printDirectory(longFilesDir, "",isFileBigFlag);
        System.out.println("Short Files");
        isFileBigFlag = false;
        Directory shortFilesDir = gson.fromJson(json.get("Short_File"),Directory.class);
        printDirectory(shortFilesDir, "",isFileBigFlag);      
    }
    
     public static void printDirectory(Directory dir, String formatter,boolean isFileBigFlag){
        System.out.println(formatter+""+dir.getName());
        formatter = formatter+"\t";
        for(FileInfo f : dir.getFiles()){
            System.out.print(formatter + f.getFileName() + "\t" + f.getCount());
            System.out.print("\t");
            if(isFileBigFlag) {
                for (Map.Entry<String, Integer> entry : f.getWordFrequency().entrySet()) {
                    String key = entry.getKey();
                    int value = entry.getValue();
                    System.out.print(key + "  " + value + "  ");
                }
            }
            System.out.println();
        }
        for(Directory f : dir.getDirectories()){
            printDirectory(f,formatter,isFileBigFlag);
        }
    }
}