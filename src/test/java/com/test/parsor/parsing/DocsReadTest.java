package com.test.parsor.parsing;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DocsReadTest {

    private final String EXISTS_FILE_PATH = "C:\\Users\\user\\Desktop\\github\\parsor\\src\\main\\resources\\static\\files\\termm.docx";
    private final String NONE_FILE_PATH = "C:\\Users\\user\\Desktop\\github\\parsor\\src\\main\\resources\\static\\files\\none.docx";

    @Test()
    @DisplayName("절대 경로 docx파일 읽어서 출력하기")
    void AbsolutePathDocsReadTest() throws  IOException {

        // Given : 절대 경로의 docx파일이 주어지면
        FileInputStream fis = new FileInputStream(EXISTS_FILE_PATH);
        XWPFDocument document = new XWPFDocument(fis);

        // When : List에 한줄씩 String으로 담는다
        ArrayList<String> word = new ArrayList<>();
        int line = 0;

        for (XWPFParagraph paragraph : document.getParagraphs()) {
            String text = paragraph.getText();
            word.add(text);
            line += 1;
        }

        // Then : List의 요소는 비어있지 않는다.
        assertThat(word).isNotEmpty().hasSize(line);
        word.stream().forEach(System.out::println);

    }

    @Test()
    @DisplayName("절대 경로 docx파일이 없는 경우")
    void AbsolutePathDocsReadFailedTest() throws  IOException {

        // Given : 절대 경로의 docx 파일이 주어지면
        final String FILE_PATH = NONE_FILE_PATH;

        // When & Then : FileInputStream 생성 시 FileNotFoundException이 발생하는지 확인
        assertThrows(FileNotFoundException.class, () -> {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            XWPFDocument document = new XWPFDocument(fis);

            ArrayList<String> word = new ArrayList<>();
            int line = 0;

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String text = paragraph.getText();
                word.add(text);
                line += 1;
            }
        });

    }

    @Test()
    @DisplayName("docx파일의 유무를 확인")
    void checkFileTest() throws  IOException {

        // Given : 절대 경로의 docx 파일이 주어지면
        final String FILE_PATH = NONE_FILE_PATH;

        // When : 파일이 있는지 확인
        File file = new File(FILE_PATH);
        
        //Then : 없는 파일은 false값 출력
        Assertions.assertThat(file.exists()).isFalse();

    }

    @Test
    @DisplayName("파일 이름만 주어졌을때 파일의 존재 유무 확인")
    void isExists() throws IOException {

        // Given : 파일 이름만 주어졌을때
        String fileName = "termm.docx";
        ClassPathResource resource = new ClassPathResource("static/files/" + fileName);

        // When
        File file = resource.getFile();

        // Then
        assertThat(file.exists()).isTrue();

    }
    
    @Test
    @DisplayName("Parser인터페이스의 구현체 테스트")
    void DocsReadTest() {
    
        // Given
        Parser parser = new ParserImpl();

        // When
        final String FILE_NAME = "none.docx";

        // Then
        boolean check = parser.isExists(FILE_NAME);
        //assertThat(check).isTrue();
        assertThat(check).isFalse();

    }

}//End of class
