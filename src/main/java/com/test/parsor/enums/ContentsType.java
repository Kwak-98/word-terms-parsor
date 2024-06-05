package com.test.parsor.enums;

public enum ContentsType {

    SUB_SECTION("관"),
    ARTICLE("조"),
    PARAGRAPH("항"),
    SUB_PARAGRAPH("호"),
    ITEM("목"),
    RANDOM("Random"),
    TABLE("Table"),;

    private final String value;

    ContentsType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
