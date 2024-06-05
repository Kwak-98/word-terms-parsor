package com.test.parsor.word;

import com.test.parsor.enums.ContentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class TextParagraphElement extends AbstractElement{

    @Builder
    public TextParagraphElement(AbstractElement parent, AbstractElement[] children, ContentsType contentsType
            , Integer no, String title, String text) {
        this.parent = parent;
        this.children = children;
        this.contentsType = contentsType;
        this.no = no;
        this.text = text;
    }

}//End of class
