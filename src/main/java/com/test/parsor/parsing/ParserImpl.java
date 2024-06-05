package com.test.parsor.parsing;

import com.test.parsor.enums.ContentsType;
import com.test.parsor.util.Regex;
import com.test.parsor.word.AbstractElement;
import com.test.parsor.word.ParagraphElement;
import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<AbstractElement> parse(XWPFDocument document) {

        List<AbstractElement> word = new ArrayList<>();

        //document를 순회하면서 객체화 -> 트리구조
        for(IBodyElement element : document.getBodyElements()) {

            // paragraph인 경우
            if(element instanceof XWPFParagraph) {
                XWPFParagraph paragraph = (XWPFParagraph) element;

                String text = paragraph.getText();

                if(text.equals("")) {
                    continue;   //엔터 삭제
                }else {
                    word.add(toParagraph(text));
                    System.out.println(text);
                }

            // table인 경우
            }else if(element instanceof XWPFTable) {
                XWPFTable table = (XWPFTable) element;

                word.add(toTable(table));

            }

        }

        //순서대로 완성된 List를 트리구조로 변경
        Optional<AbstractElement> root = createTree(word);

        //사용이 끝난 List 메모리 해제
        word = null;

        return root;
    }

    public Optional<AbstractElement> createTree(List<AbstractElement> word) {
        return Optional.empty();
    }

    public AbstractElement toParagraph(String text) {

        if (Regex.isSubSection(text)) {

            int startTitle = text.indexOf("제");
            int endTitle = text.indexOf("관");

            System.out.println(text.substring(endTitle + 2));

            AbstractElement paragraph = ParagraphElement.builder()
                    .contentsType(ContentsType.SUB_SECTION)
                    .title(text.substring(startTitle, endTitle + 1))
                    .no(Integer.parseInt(text.substring(startTitle + 2, endTitle - 1)))
                    .text(text.substring(endTitle + 2))
                    .build();

            return paragraph;

        } else if(Regex.isArticle(text)) {

        }

        return  null;
    }

    public AbstractElement toTable(XWPFTable table) {
        return null;
    }

//    AbstractElement[] child = new AbstractElement[table.getNumberOfRows()];
//
//                for(XWPFTableRow row : table.getRows()) {
//
//        List<XWPFTableCell> cells = row.getTableCells();
//        System.out.println(cells.size());
//        cells.stream().forEach(cell -> System.out.println(cell.getText()));
//    }

}//End of class
