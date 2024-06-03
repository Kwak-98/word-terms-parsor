package com.test.parsor.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class FileServiceImpl implements FileService{


    @Override
    public Boolean fileSave(MultipartFile file) {

        final String FILE_PATH = (new ClassPathResource("static/files/" + file.getOriginalFilename()).toString();

        (new ClassPathResource("static/files/" + fileName).getFile().getAbsolutePath())

        return null;
    }

}//End of class
