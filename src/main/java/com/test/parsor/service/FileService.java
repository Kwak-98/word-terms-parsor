package com.test.parsor.service;

import com.test.parsor.word.AbstractElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FileService {

    XWPFDocument readFile(MultipartFile file);

}
