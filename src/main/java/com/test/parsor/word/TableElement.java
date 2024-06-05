package com.test.parsor.word;

import com.test.parsor.enums.ContentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableElement extends AbstractElement{

    private AbstractElement[] rows;

    @Builder
    public TableElement(AbstractElement parent, AbstractElement[] children, ContentsType contentsType
            , Integer no, AbstractElement[] rows, String text) {
        this.parent = parent;
        this.children = children;
        this.contentsType = contentsType;
        this.no = no;
        this.rows = rows;
        this.text = text;
    }

}//End of class
