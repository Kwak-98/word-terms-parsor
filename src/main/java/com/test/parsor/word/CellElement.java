package com.test.parsor.word;

import com.test.parsor.enums.ContentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CellElement extends AbstractElement{

    private AbstractElement element;

    @Builder
    public CellElement(AbstractElement parent, AbstractElement[] children, ContentsType contentsType
            , Integer no, AbstractElement element, String text) {
        this.parent = parent;
        this.children = children;
        this.contentsType = contentsType;
        this.no = no;
        this.element = element;
        this.text = text;
    }


}//End of class
