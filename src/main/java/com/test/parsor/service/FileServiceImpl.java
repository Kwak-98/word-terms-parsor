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

        // docx파일을 apache poi라이브러로 read List로 반환
        // Stream과 Documnet는 close해야되기 때문에 try-with-resources사용
        try (
            InputStream inputStream = file.getInputStream();
            XWPFDocument document = new XWPFDocument(inputStream);
        ){
            // List에 각 줄마다 String으로 변환후 반환

            document.getBodyElements().stream().forEach(element -> System.out.println(element.getElementType().toString()));



            document.getParagraphs().stream().forEach(paragraph -> word.add(paragraph.getText()));
            document.getTables().stream().forEach(table ->
                        table.getRows().stream().forEach(row ->
                                row.getTableCells().stream().forEach(cell -> word.add(cell.getText())
                                )
                        )
            );

            return word;

        } catch (IOException e) {
            return word;
        }

    }

}//End of class
