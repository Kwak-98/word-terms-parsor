package com.test.parsor.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public List<String> readFile(MultipartFile file) {

        List<String> word = new ArrayList<>();

        //파일이 비어있는 경우 null 반환
        if(file.isEmpty()) {
            return word;
        }

        //docx파일을 바로 읽어서 List로 반환
        try (
            InputStream inputStream = file.getInputStream();
            XWPFDocument document = new XWPFDocument(inputStream);
        ){
            // List에 각 줄마다 String으로 변환후 반환
            document.getParagraphs().stream().forEach(paragraph -> word.add(paragraph.getText()));
            return word;

        } catch (IOException e) {
            return word;
        }

    }

}//End of class
