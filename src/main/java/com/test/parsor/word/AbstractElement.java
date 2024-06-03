package com.test.parsor.word;

import com.test.parsor.enums.ContentsType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
abstract class AbstractElement {

    private AbstractElement parent;         // 부모 객체
    private ContentsType contentsType;      // 관, 조, 항 등의 타입
    private Integer no;                     // 번호
    private String text;                    // paragraph 내용

    public void print() {

    }

    public String  getContentsType() {
        return getContentsType().toString();
    }

    public Integer getNo() {
        return getNo();
    }

    public AbstractElement[] getChildren() {

        return null;
    }

    public AbstractElement getParent() {
        return getParent();
    }

    public String getText() {
        return getText();
    }

    public RowElement[] getRows() {
        return null;
    }

    public CellElement getCells() {
        return null;
    }

    public  AbstractElement[] getElements() {
        return null;
    }

}//End of class
