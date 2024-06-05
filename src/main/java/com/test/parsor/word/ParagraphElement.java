package com.test.parsor.word;

import com.test.parsor.enums.ContentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParagraphElement extends AbstractElement{

    private String title;

    @Builder
    public ParagraphElement(AbstractElement parent, AbstractElement[] children, ContentsType contentsType
                            , Integer no, String title, String text) {
        this.parent = parent;
        this.children = children;
        this.contentsType = contentsType;
        this.no = no;
        this.title = title;
        this.text = text;
    }

}//End of class
