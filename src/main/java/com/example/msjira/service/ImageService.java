package com.example.msjira.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {

    public String createAndReturnPath(MultipartFile file,String folderName){
        String folderPath = System.getProperty("user.dir")+"/uploads/"+folderName;
        String filepath = folderPath + "/" + file.getOriginalFilename()+"_"+System.currentTimeMillis();
        try {
            file.transferTo(new File(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(file!=null){
            return filepath;
        }
        return null;
    }

}
