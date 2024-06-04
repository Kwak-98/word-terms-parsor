package com.test.parsor.parsing;

import com.test.parsor.word.AbstractElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * word파일만을 파싱가능한 parsing가능한 구현체
 */
@Configuration
public class ParserImpl implements Parser{


    @Override
    public List<String> toList(String fileName) throws IOException {

        // docx 파일을 apache poi로 read
        FileInputStream fis = new FileInputStream((new ClassPathResource("static/files/" + fileName).getFile().getAbsolutePath()));
        XWPFDocument document = new XWPFDocument(fis);
        
        // List에 각 줄마다 String으로 변환후 반환
        List<String> word = new ArrayList<>();
        document.getParagraphs().stream().forEach(paragraph -> word.add(paragraph.getText()));

        return word;
    }

    @Override
    public AbstractElement parse(String fileName, List<String> word) {
        return null;
    }
}//End of class
