package com.test.parsor.word;

import com.test.parsor.enums.ContentsType;
import com.test.parsor.util.Regex;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public abstract class AbstractElement {

    protected static String tab = "  ";

    protected AbstractElement parent;          // 부모 객체
    protected List<AbstractElement> children;  // 자식 객체
    protected ContentsType contentsType;       // 관, 조, 항 등의 타입
    protected Integer no;                      // 번호
    protected String text;                     // 내용
    protected Integer depth;                   // 탭의 깊이

    /**
     * 루트부터 깊이우선탐색하며 콘솔에 출력하는 메서드
     */
    public void print() {
        print(0);
    }

    /**
     * 객체의 타입에 따라 콘솔에 다른 결과 출력
     * 자식이 존재할시 재귀호출 > 출력 반복
     * 자식이 없을경우 > 재귀호출 종료 조건
     * @param space : 텍스트 앞에 공백을 위한 변수
     *                부모에서 자식으로 깊어질때마다 space증가
     */
    private void print(int space) {

        if(this.contentsType == ContentsType.SENTENCE) {
            System.out.println(this.text);
        } else if(this.contentsType == ContentsType.SUB_SECTION) {
            System.out.println(tab.repeat(space) + ((ParagraphElement)this).getTitle() + " " + this.text);
        } else if(this.contentsType == ContentsType.ARTICLE) {
            ParagraphElement article =  (ParagraphElement)this;

            System.out.println(tab.repeat(space) + article.getTitle());
            if(article.text != null) {
                System.out.println(tab.repeat(space) + " " + article.text);
            }
        } else if(this.contentsType == ContentsType.PARAGRAPH) {
            System.out.println(tab.repeat(space) + Regex.numberToCircle(this.no) + " " + this.text);
        } else if(this.contentsType == ContentsType.SUB_PARAGRAPH) {
            System.out.println(tab.repeat(space) + this.no + ". " + this.text);
        } else if(this.contentsType == ContentsType.ITEM) {
            System.out.println(tab.repeat(space) + Regex.numberToKorean(this.no) + ". " + this.text);
        } else if(this.contentsType == ContentsType.RANDOM) {
            System.out.println(tab.repeat(space) + this.text);
        } else if(this.contentsType == ContentsType.TABLE) {
            System.out.println(tab.repeat(space) + "--------------------");
            TableElement table =  (TableElement)this;
            for(RowElement row : table.getRows()) {
                for(CellElement cell : row.getCells()) {
                    for(AbstractElement element : cell.getElements()) {
                        System.out.print(tab.repeat(space) + element.getText() + ",");
                    }
                    System.out.print("|");
                }
                System.out.println();
            }
            System.out.println(tab.repeat(space) + "--------------------");
        }

        //재귀 호출 중단조건
        if(this.children == null) {
            return;
        }

        space++;

        //조건에 걸리지 않으면 자식객체 탐색
        for(AbstractElement child : this.children) {
            child.print(space);
        }

    }

    public AbstractElement getParent() {
        return this.parent;
    }

    public AbstractElement[]  getChildren() {
        if(this.children == null) {
            return null;
        }
        return this.children.toArray(new AbstractElement[this.children.size()]);
    }

    public void addChildren(AbstractElement children) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(children);
    }

    public ContentsType getContentsType() {
        return this.contentsType;
    }

    public Integer getNo() {
        return this.no;
    }

    public String getText() {
        return this.text;
    }

    public Integer getDepth() {
        return this.depth;
    }

}//End of class
