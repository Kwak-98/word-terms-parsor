package com.test.parsor.service;

import com.test.parsor.parsing.Parser;
import com.test.parsor.word.AbstractElement;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    private final Parser PARSER;

    @Override
    public XWPFDocument readFile(MultipartFile file) {

        //파일이 비어있는 경우 null 반환
        if(file.isEmpty()) {
            return null;
        }

        // docx파일을 apache poi라이브러로 XWPFDocument로 반환
        // Stream과 Documnet는 close해야되기 때문에 try-with-resources사용
        try (
            InputStream inputStream = file.getInputStream();
            XWPFDocument document = new XWPFDocument(inputStream);
        ){

            return document;

        } catch (IOException e) {
            return null;
        }

    }

}//End of class
