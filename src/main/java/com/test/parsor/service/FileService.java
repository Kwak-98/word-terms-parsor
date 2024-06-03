package com.test.parsor.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public Boolean fileSave(MultipartFile file);

}
