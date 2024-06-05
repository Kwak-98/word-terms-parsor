package com.test.parsor.word;

import com.test.parsor.enums.ContentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public abstract class AbstractElement {

    protected AbstractElement parent;          // 부모 객체
    protected AbstractElement[] children;      // 자식 객체
    protected ContentsType contentsType;       // 관, 조, 항 등의 타입
    protected Integer no;                      // 번호
    protected String text;                     // 내용

    public void print() {

    }

}//End of class
