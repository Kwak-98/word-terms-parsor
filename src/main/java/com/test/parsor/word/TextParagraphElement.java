package com.test.parsor.word;

import com.test.parsor.enums.ContentsType;
import lombok.Builder;

import java.util.List;


public class TextParagraphElement extends AbstractElement{

    @Builder
    public TextParagraphElement(AbstractElement parent, List<AbstractElement> children, ContentsType contentsType
            , Integer no, String text, Integer depth) {
        this.parent = parent;
        this.children = children;
        this.contentsType = contentsType;
        this.no = no;
        this.text = text;
        this.depth = depth;
    }

}//End of class
