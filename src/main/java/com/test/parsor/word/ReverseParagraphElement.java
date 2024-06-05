package com.test.parsor.word;

import com.test.parsor.enums.ContentsType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReverseParagraphElement extends AbstractElement {

    private String reverseTitle;

    @Builder
    public ReverseParagraphElement(AbstractElement parent, AbstractElement[] children, ContentsType contentsType
            , Integer no, String reverseTitletitle, String text) {
        this.parent = parent;
        this.children = children;
        this.contentsType = contentsType;
        this.no = no;
        this.reverseTitle = reverseTitletitle;
        this.text = text;
    }

}//End of class
