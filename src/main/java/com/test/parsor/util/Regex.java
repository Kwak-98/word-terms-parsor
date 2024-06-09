package com.test.parsor.util;

import java.util.regex.Pattern;

/**
 * 문장을 정규표현식으로 분석하고 패턴과 일치여부를 Boolean으로 반환
 * 한글이나 특정 유니코드를 숫자로 또는 역방향으로 변환
 */
public class Regex {

    private static final String SUB_SECTION = "^제\\s\\d+\\s관\\s[가-힣A-Za-z0-9\\s]+$";
    private static final String ARTICLE = "^제\\d+조\\([가-힣A-Za-z0-9\\s'‘’]+\\)$";
    private static final String PARAGRAPH = "^[\u2460-\u2473].*$";
    private static final String SUB_PARAGRAPH = "^\\d+\\.\\s*.*$";
    private static final String ITEM = "^[가나다라마바사아자차카타파하]\\..*$";
    private static final String RANDOM = "^(?:\\d+\\)|\\(\\d+\\)|[ㄱ-ㅎ-|가-힣]\\)|[\u24B6-\u24E9\u3260-\u327B])\\s.*";

    public static Boolean isSubSection(String text) {
        return Pattern.matches(SUB_SECTION, text);
    }

    public static Boolean isArticle(String text) {
        return Pattern.matches(ARTICLE, text);
    }

    public static Boolean isParagraph(String text) {
        return Pattern.matches(PARAGRAPH, text);
    }

    public static Boolean isSubParagraph(String text) {
        return Pattern.matches(SUB_PARAGRAPH, text);
    }

    public static Boolean isItem(String text) {
        return Pattern.matches(ITEM, text);
    }

    public static boolean isRandom(String text) {
        return Pattern.matches(RANDOM, text);
    }

    public static char numberToCircle(Integer number) {
        switch (number) {
            case 1: return '\u2460';
            case 2: return '\u2461';
            case 3: return '\u2462';
            case 4: return '\u2463';
            case 5: return '\u2464';
            case 6: return '\u2465';
            case 7: return '\u2466';
            case 8: return '\u2467';
            case 9: return '\u2468';
            case 10: return '\u2469';
            case 11: return '\u246A';
            case 12: return '\u246B';
            case 13: return '\u246C';
            case 14: return '\u246D';
            case 15: return '\u246E';
            case 16: return '\u246F';
            case 17: return '\u2470';
            case 18: return '\u2471';
            case 19: return '\u2472';
            case 20: return '\u2473';
            default: return '\u003F';
        }
    }

    public static Integer circleToNumber(char circle) {
        switch (circle) {
            case '\u2460': return 1;
            case '\u2461': return 2;
            case '\u2462': return 3;
            case '\u2463': return 4;
            case '\u2464': return 5;
            case '\u2465': return 6;
            case '\u2466': return 7;
            case '\u2467': return 8;
            case '\u2468': return 9;
            case '\u2469': return 10;
            case '\u246A': return 11;
            case '\u246B': return 12;
            case '\u246C': return 13;
            case '\u246D': return 14;
            case '\u246E': return 15;
            case '\u246F': return 16;
            case '\u2470': return 17;
            case '\u2471': return 18;
            case '\u2472': return 19;
            case '\u2473': return 20;
            default: return 0;
        }
    }

    public static Integer koreanToNumber(String korean) {
        switch (korean) {
            case "가": return 1;
            case "나": return 2;
            case "다": return 3;
            case "라": return 4;
            case "마": return 5;
            case "바": return 6;
            case "사": return 7;
            case "아": return 8;
            case "자": return 9;
            case "차": return 10;
            case "카": return 11;
            case "타": return 12;
            case "파": return 13;
            case "하": return 14;
            default: return 0;
        }
    }

    public static String numberToKorean(Integer no) {
        switch (no) {
            case 1: return "가";
            case 2: return "나";
            case 3: return "다";
            case 4: return "라";
            case 5: return "마";
            case 6: return "바";
            case 7: return "사";
            case 8: return "아";
            case 9: return "자";
            case 10: return "차";
            case 11: return "카";
            case 12: return "타";
            case 13: return "파";
            case 14: return "하";
            default: return "예외";
        }
    }

}//End of class
