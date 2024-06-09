package com.test.parsor.word;

import com.test.parsor.enums.ContentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CellElement extends AbstractElement{

    private AbstractElement[] elements;

    @Builder
    public CellElement(AbstractElement parent, List<AbstractElement> children, ContentsType contentsType
            , Integer no, AbstractElement[] elements, String text) {
        this.parent = parent;
        this.children = children;
        this.contentsType = contentsType;
        this.no = no;
        this.elements = elements;
        this.text = text;
    }


}//End of class
