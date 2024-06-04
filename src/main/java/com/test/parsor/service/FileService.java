package com.test.parsor.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    List<String> readFile(MultipartFile file);

}
