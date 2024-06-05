package com.test.parsor.word;

import com.test.parsor.enums.ContentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RowElement extends AbstractElement{

    private AbstractElement[] cells;

    @Builder
    public RowElement(AbstractElement parent, AbstractElement[] children, ContentsType contentsType
            , Integer no, AbstractElement[] cells, String text) {
        this.parent = parent;
        this.children = children;
        this.contentsType = contentsType;
        this.no = no;
        this.cells = cells;
        this.text = text;
    }

}//End of class
