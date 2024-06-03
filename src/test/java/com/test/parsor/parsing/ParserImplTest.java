package com.test.parsor.parsing;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ParserImplTest {

    @Autowired
    Parser parser;

    @Test
    @DisplayName("docx파일의 유무를 확인하고 있으면 List반환")
    void test() throws IOException {
    
        //Given : 파일의 이름
        final String FILE_NAME = "termm.docx";

        //When : 파일이 존재하면
        Boolean exists = parser.isExists(FILE_NAME);

        //Then
        FileInputStream fis = new FileInputStream((new ClassPathResource("static/files/" + FILE_NAME).getFile().getAbsolutePath()));
        XWPFDocument document = new XWPFDocument(fis);

        ArrayList<String> word = new ArrayList<>();
        int line = 0;

        for (XWPFParagraph paragraph : document.getParagraphs()) {
            String text = paragraph.getText();
            word.add(text);
            line += 1;
        }

        assertThat(word.size()).isEqualTo(line);


    }

    @Test
    @DisplayName("파일이 존재하면 List를 반환하는지 확인하는 테스트")
    void toListTest() throws IOException {
    
        //Given : 파일의 이름이 주어지면
        final String FILE_NAME = "termm.docx";

        //When : 폴더에 존재하는 파일이면
        Boolean exists = parser.isExists(FILE_NAME);


        //Then : List는 null이 아니다.
        List<String> word = null;

        if(exists) {
            word = parser.toList(FILE_NAME);
        }

        assertThat(word).isNotNull();
    
    }

}