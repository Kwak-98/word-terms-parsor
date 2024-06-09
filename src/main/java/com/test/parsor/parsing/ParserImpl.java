package com.test.parsor.parsing;

import com.test.parsor.enums.ContentsType;
import com.test.parsor.util.Regex;
import com.test.parsor.word.*;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * word파일만을 파싱가능한 parsing가능한 구현체
 */
@Configuration
public class ParserImpl implements Parser{

    @Override
    public List<String> toHtml(List<AbstractElement> word) {
        List<String> htmlList = new ArrayList<>();

        for (AbstractElement element : word) {
            if (element.getContentsType() == ContentsType.SENTENCE) {
                htmlList.add("<h2 class='title'>" + element.getText() + "</h2>");
            } else if (element.getContentsType() == ContentsType.SUB_SECTION) {
                htmlList.add("<p class='sub-section'>" + ((ParagraphElement) element).getTitle() + "&nbsp;" + element.getText() + "</p>");
            } else if (element.getContentsType() == ContentsType.ARTICLE) {
                ParagraphElement article = (ParagraphElement) element;
                htmlList.add("<p class='article'>" + article.getTitle() + "</p>");
                if (article.getText() != null) {
                    htmlList.add("<p class='article-text'>" + article.getText() + "</p>");
                }
            } else if (element.getContentsType() == ContentsType.PARAGRAPH) {
                htmlList.add("<p class='paragraph'>" + "&nbsp;".repeat(element.getDepth()) + Regex.numberToCircle(element.getNo()) + "&nbsp;" + element.getText() + "</p>");
            } else if (element.getContentsType() == ContentsType.SUB_PARAGRAPH) {
                htmlList.add("<p class='sub-paragraph'>" + "&nbsp;".repeat(element.getDepth()) + element.getNo() + ".&nbsp;" + element.getText() + "</p>");
            } else if (element.getContentsType() == ContentsType.ITEM) {
                htmlList.add("<p class='item'>" + "&nbsp;".repeat(element.getDepth()) + Regex.numberToKorean(element.getNo()) + ".&nbsp;" + element.getText() + "</p>");
            } else if (element.getContentsType() == ContentsType.RANDOM) {
                htmlList.add("<p class='random'>" + "&nbsp;".repeat(element.getDepth()) + element.getText() + "</p>");
            } else if (element.getContentsType() == ContentsType.TABLE) {
                htmlList.add("<table class='table'>");
                TableElement table = (TableElement) element;
                for (RowElement row : table.getRows()) {
                    htmlList.add("<tr>");
                    for (CellElement cell : row.getCells()) {
                        htmlList.add("<td>");
                        for (AbstractElement cellElement : cell.getElements()) {
                            htmlList.add(cellElement.getText() + ",");
                        }
                        htmlList.add("</td>");
                    }
                    htmlList.add("</tr>");
                }
                htmlList.add("</table>");
            }
        }

        return htmlList;
    }

    @Override
    public List<AbstractElement> toList(AbstractElement element) {
        List<AbstractElement> list = new ArrayList<>();
        toListRecursion(element, list, 0);
        return list;
    }

    private void toListRecursion(AbstractElement element, List<AbstractElement> list, int depth) {
        element.setDepth(depth);
        list.add(element);

        if (element.getChildren() != null) {
            for (AbstractElement children : element.getChildren()) {
                toListRecursion(children, list, depth + 1);
            }
        }
    }

    @Override
    public Optional<AbstractElement> parse(XWPFDocument document) {

        List<AbstractElement> word = new ArrayList<>();

        //document를 순회하면서 객체화
        for(IBodyElement element : document.getBodyElements()) {

            // paragraph인 경우
            if(element instanceof XWPFParagraph) {
                XWPFParagraph paragraph = (XWPFParagraph) element;

                if(paragraph.getText().equals("")) {
                    continue;   //엔터 삭제
                }else {
                    // null이 아니면 객체 -> List에 추가
                    AbstractElement paragraphElement = toParagraph(paragraph);
                    if(paragraphElement != null) {
                        word.add(paragraphElement);
                    }
                }

            // table인 경우
            }else if(element instanceof XWPFTable) {
                XWPFTable table = (XWPFTable) element;
                word.add(toTable(table));
            }

        }

        //순서대로 완성된 List를 트리구조로 변경
        Optional<AbstractElement> result = createTree(word);

        //사용이 끝난 List 메모리 해제
        word = null;

        return result;
    }

    public Optional<AbstractElement> createTree(List<AbstractElement> word) {

        //루트 지정
        AbstractElement root = word.get(0);

        //몇개의 관으로 나뉘는지 확인
        List<Integer> divider = new ArrayList<>();
        for(int i = 0 ; i < word.size() ; i++) {
            if(word.get(i).getContentsType() ==ContentsType.SUB_SECTION) {
                divider.add(i);
            }
        }

        //루트의 자식배열
        List<AbstractElement> subSections = new ArrayList<>();
        root.setChildren(subSections);

        //관을 기준으로 트리구조 생성
        for(int i = 0; i < divider.size(); i++) {
            int startIndex = divider.get(i);                                                 // 시작 인덱스
            int endIndex = (i == divider.size()-1) ? word.size() : divider.get(i + 1);       // 종료 인덱스(마지막 관은 outOfBound방지)

            List<AbstractElement> subSectionDivider = word.subList(startIndex, endIndex);    // 잘린 리스트
            AbstractElement subSection = connectSubSection(subSectionDivider);               // 트리 구조 루트 반환
            subSection.setParent(root);                                                      // 부모 지정
            subSections.add(subSection);                                                     // 자식에 추가
        }

        return Optional.of(root);
    }

    private AbstractElement connectSubSection(List<AbstractElement> subSectionDivider) {
        AbstractElement subSection = subSectionDivider.get(0);  // SubSection루트 지정

        //몇개의 조로 나뉘는지 확인
        List<Integer> divider = new ArrayList<>();
        for(int i = 0 ; i < subSectionDivider.size() ; i++) {
            if(subSectionDivider.get(i).getContentsType() ==ContentsType.ARTICLE) {
                divider.add(i);
            }
        }

        //SubSection의 자식배열
        List<AbstractElement> articles = new ArrayList<>();
        subSection.setChildren(articles);

        for(int i = 0; i < divider.size(); i++) {
            int startIndex = divider.get(i);                                                              // 시작 인덱스
            int endIndex = (i == divider.size()-1) ? subSectionDivider.size() : divider.get(i + 1);       // 종료 인덱스(마지막은 outOfBound방지)

            List<AbstractElement> articleDivider = subSectionDivider.subList(startIndex, endIndex);       // 잘린 리스트
            AbstractElement article = connectArticle(articleDivider);                                     // 트리 구조 루트 반환
            article.setParent(subSection);                                                                // 부모 지정
            articles.add(article);                                                 // 자식에 추가
        }

        return subSection;
    }

    private AbstractElement connectArticle(List<AbstractElement> articleDivider) {
        AbstractElement article = articleDivider.get(0);

        //조 다음에 자식항목이 아닌 일반 문장이 나오는 경우
        if(articleDivider.size() > 1 && articleDivider.get(1).getContentsType() == ContentsType.SENTENCE) {
            article.setText(articleDivider.get(1).getText());
        }

        AbstractElement previousElement = article;  // 이전요소, 초기값은 루트
        int previousDepth = article.getDepth();     // 이전요소의 탭깊이

        for (int i = 1; i < articleDivider.size(); i++) {
            AbstractElement currentElement = articleDivider.get(i);

            // 일반 문장 통과
            if (currentElement.getContentsType() == ContentsType.SENTENCE) {
                continue;
            }

            // 테이블인 경우
            if (currentElement instanceof TableElement) {
                AbstractElement parent = previousElement.getParent();
                if (parent == null) {
                    parent = article;
                }
                parent.addChildren(currentElement);
                currentElement.setParent(parent);
                continue;
            }
            
            //테이블 이후의 depth 설정
            int currentDepth = currentElement.getDepth();

            if (currentDepth == previousDepth) {                        // depth가 이전과 같은 경우 (형제로 이동)
                AbstractElement parent = previousElement.getParent();
                parent.addChildren(currentElement);
                currentElement.setParent(parent);
            } else if (currentDepth > previousDepth) {                  // depth가 이전보다 큰 경우 (자식으로 이동)
                previousElement.addChildren(currentElement);
                currentElement.setParent(previousElement);
            } else {                                                    // depth가 이전보다 작은 경우 (부모로 이동)
                AbstractElement parent = previousElement.getParent();
                while (parent != null && parent.getDepth() >= currentDepth) {
                    parent = parent.getParent();
                }
                if (parent == null) {
                    parent = article;
                }
                parent.addChildren(currentElement);
                currentElement.setParent(parent);
            }

            previousElement = currentElement;
            previousDepth = currentDepth;
        }

        return article;
    }

    /**
     * XWPFParagraph타입의 객체일 경우 AbstractElement객체로 변환후 반환
     * @param text : word의 텍스트 한줄
     * @return AbstractElement객체로 반환
     */
    public AbstractElement toParagraph(XWPFParagraph paragraph) {

        String text = paragraph.getText();
        Integer depth = paragraph.getIndentationLeft();

        if (Regex.isSubSection(text)) {

            int startTitle = text.indexOf("제");
            int endTitle = text.indexOf("관");

            return ParagraphElement.builder()
                    .contentsType(ContentsType.SUB_SECTION)
                    .title(text.substring(startTitle, endTitle + 1))
                    .no(Integer.parseInt(text.substring(startTitle + 2, endTitle - 1)))
                    .text(text.substring(endTitle + 2))
                    .depth(depth)
                    .build();

        } else if(Regex.isArticle(text)) {

            int startTitle = text.indexOf("제");
            int endTitle = text.indexOf("조");

            return ParagraphElement.builder()
                    .contentsType(ContentsType.ARTICLE)
                    .title(text)
                    .no(Integer.parseInt(text.substring(startTitle + 1, endTitle)))
                    .depth(depth)
                    .build();

        } else if(Regex.isParagraph(text)) {

            return TextParagraphElement.builder()
                    .contentsType(ContentsType.PARAGRAPH)
                    .no(Regex.circleToNumber(text.charAt(0)))
                    .text(text.substring(2))
                    .depth(depth)
                    .build();


        } else if(Regex.isSubParagraph(text)) {

            int point = text.indexOf(".");

            return TextParagraphElement.builder()
                    .contentsType(ContentsType.SUB_PARAGRAPH)
                    .no(Integer.parseInt(text.substring(0, point)))
                    .text(text.substring(point + 1))
                    .depth(depth)
                    .build();

        } else if(Regex.isItem(text)) {

            int point = text.indexOf(".");

            return TextParagraphElement.builder()
                    .contentsType(ContentsType.ITEM)
                    .no(Regex.koreanToNumber(text.charAt(0) + ""))
                    .text(text.substring(point + 1))
                    .depth(depth)
                    .build();

        } else if(Regex.isRandom(text)) {
            return TextParagraphElement.builder()
                    .contentsType(ContentsType.RANDOM)
                    .text(text)
                    .depth(depth)
                    .build();
        }
        else {
            return TextParagraphElement.builder()
                    .contentsType(ContentsType.SENTENCE)
                    .text(text)
                    .depth(depth)
                    .build();
        }

    }

    /**
     * XWPFTable타입의 객체일 경우 트리구조의 AbstractElement객체로 변환후 반환
     * @param table : 하나의 테이블
     * @return 트리구조의 AbstractElement객체의 루트 객체 반환
     */
    public AbstractElement toTable(XWPFTable table) {
        int rowNum = table.getNumberOfRows();

        //테이블 객체 생성
        TableElement tableElement = TableElement.builder()
                .contentsType(ContentsType.TABLE)
                .build();
        
        //테이블 row배열 생성
        RowElement[] rows = new RowElement[rowNum];
        tableElement.setRows(rows);

        for(int i = 0; i < rowNum; i++) {
            //row객체 생성
            XWPFTableRow row = table.getRow(i);
            RowElement rowElement = RowElement.builder()
                            .contentsType(ContentsType.ROW)
                            .no(i + 1)
                            .build();
            rows[i] = rowElement;
            
            // Cell배열 생성
            int cellNum = row.getTableCells().size();
            CellElement[] cells = new CellElement[cellNum];
            rowElement.setCells(cells);

            for(int j = 0; j < cellNum; j++) {
                XWPFTableCell cell = row.getTableCells().get(j);
                CellElement cellElement = CellElement.builder()
                                .contentsType(ContentsType.CELL)
                                .no(j + 1)
                                .build();
                cells[j] = cellElement;

                String text = cell.getText();

                //cell안에 객체 배열 생성
                if(text.contains(",")) {
                    StringTokenizer tokenizer = new StringTokenizer(text, ",");
                    int elementNum = tokenizer.countTokens();

                    AbstractElement[] elements = new AbstractElement[elementNum];
                    for(int k = 0; k < elementNum; k++) {
                        elements[k] = TextParagraphElement.builder()
                                .contentsType(ContentsType.ELEMENT)
                                .no(k + 1)
                                .text(tokenizer.nextToken())
                                .build();
                    }
                    cellElement.setElements(elements);
                }else {
                    AbstractElement[] elements = new AbstractElement[1];
                    elements[0] = TextParagraphElement.builder()
                            .contentsType(ContentsType.ELEMENT)
                            .no(1)
                            .text(text)
                            .build();
                    cellElement.setElements(elements);
                }//element 객체 생성 종료

            }//cell객체 생성 종료

        }//row객체 생성 종료

        //결과 반환
        AbstractElement result = (AbstractElement) tableElement;

        return result;
    }

}//End of class
