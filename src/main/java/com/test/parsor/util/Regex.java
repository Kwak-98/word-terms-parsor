package com.test.parsor.util;

import java.util.regex.Pattern;

public class Regex {

    private static final String SUB_SECTION = "^제\\s\\d+\\s관\\s[가-힣A-Za-z0-9\\s]+$";
    private static final String ARTICLE = "^제\\d+조\\([가-힣A-Za-z0-9\\s]+\\)$";

    public static Boolean isSubSection(String text) {
        return Pattern.matches(SUB_SECTION, text);
    }

    public static Boolean isArticle(String text) {
        return Pattern.matches(ARTICLE, text);
    }

//    public static Boolean isParagraph(String text) {
//
//    }
//
//    public static Boolean isSubParagraph(String text) {
//
//    }
//
//    public static Boolean isItem(String text) {
//
//    }
//
//    public static Boolean isRandom(String text) {
//
//    }

}//End of class
