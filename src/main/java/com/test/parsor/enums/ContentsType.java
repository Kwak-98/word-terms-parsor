package com.test.parsor.enums;

/**
 * 파싱한 word파일의 타입을 정의하는 열거형
 */
public enum ContentsType {

    SUB_SECTION("관"),       // 관 : 제 x 관 명칭
    ARTICLE("조"),           // 조 : 제 x 조 명칭
    PARAGRAPH("항"),         // 항 : 동그라미 숫자(①,②,③, …)
    SUB_PARAGRAPH("호"),     // 호 : 숫자+마침표(1., 2., 3., …)
    ITEM("목"),              // 목 : 한글(가나다)+마침표(가., 나., 다., …)
    RANDOM("Random"),        // 1), (1), ㄱ), (ㄱ), 가), (가), ㉮, ㉠, ⓐ등
    TABLE("Table"),          // 테이블
    ROW("Row"),              // 테이블의 행
    CELL("Cell"),            // 행의 셀
    ELEMENT("Element"),      // 셀의 요소
    SENTENCE("Sentence");    // 일반 문장

    private final String value;

    ContentsType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
