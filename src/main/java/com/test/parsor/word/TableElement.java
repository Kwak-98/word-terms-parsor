package com.test.parsor.word;

import com.test.parsor.enums.ContentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TableElement extends AbstractElement{

    private RowElement[] rows;

    @Builder
    public TableElement(AbstractElement parent, List<AbstractElement> children, ContentsType contentsType
            , Integer no, RowElement[] rows, String text) {
        this.parent = parent;
        this.children = children;
        this.contentsType = contentsType;
        this.no = no;
        this.rows = rows;
        this.text = text;
    }

}//End of class
